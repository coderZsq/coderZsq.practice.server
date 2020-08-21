package com.seemygo.shop.cloud.web.controller;

import com.seemygo.shop.cloud.domain.OrderInfo;
import com.seemygo.shop.cloud.domain.User;
import com.seemygo.shop.cloud.exception.BusinessException;
import com.seemygo.shop.cloud.mq.MQConstants;
import com.seemygo.shop.cloud.mq.MQLogSendCallback;
import com.seemygo.shop.cloud.mq.msg.CreateSeckillOrderMsg;
import com.seemygo.shop.cloud.redis.key.SeckillRedisKey;
import com.seemygo.shop.cloud.resp.Result;
import com.seemygo.shop.cloud.service.IOrderInfoService;
import com.seemygo.shop.cloud.service.ISeckillGoodService;
import com.seemygo.shop.cloud.service.ISeckillOrderService;
import com.seemygo.shop.cloud.util.CookieUtil;
import com.seemygo.shop.cloud.util.JSONUtil;
import com.seemygo.shop.cloud.vo.SeckillGoodVo;
import com.seemygo.shop.cloud.web.SeckillCodeMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController extends BaseController {

    public static final ConcurrentHashMap<Long, Boolean> STOCK_COUNT_FLAG = new ConcurrentHashMap<>();

    private final ISeckillGoodService seckillGoodService;
    private final IOrderInfoService orderInfoService;
    private final ISeckillOrderService seckillOrderService;
    private final RocketMQTemplate rocketMQTemplate;
    private final StringRedisTemplate redisTemplate;

    public OrderController(ISeckillGoodService seckillGoodService, IOrderInfoService orderInfoService, ISeckillOrderService seckillOrderService, StringRedisTemplate redisTemplate, RocketMQTemplate rocketMQTemplate) {
        this.seckillGoodService = seckillGoodService;
        this.orderInfoService = orderInfoService;
        this.seckillOrderService = seckillOrderService;
        this.redisTemplate = redisTemplate;
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @GetMapping("/{orderNo}")
    public Result<OrderInfo> orderDetail(@PathVariable("orderNo") String orderNo, @CookieValue(CookieUtil.TOKEN_IN_COOKIE) String token) {
        User user = this.getCurrentUser(token);
        if (user == null) {
            throw new BusinessException(SeckillCodeMsg.OP_ERROR);
        }

        OrderInfo orderInfo = orderInfoService.findById(orderNo, user.getId());
        return Result.success(orderInfo);
    }

    /**
     * 优化前:
     * 100 * 50
     * 1237.0/s
     *
     * @param seckillId
     * @param token
     * @param uuid
     * @return
     */
    @PostMapping
    public Result<String> doSeckill(String uuid, Long seckillId, @CookieValue(CookieUtil.TOKEN_IN_COOKIE) String token) {
        User user = this.getCurrentUser(token);
        // 基础判断
        if (seckillId == null || StringUtils.isEmpty(uuid) || user == null) {
            throw new BusinessException(SeckillCodeMsg.OP_ERROR);
        }

        // 查看本地标识库存是否已售完
        Boolean over = STOCK_COUNT_FLAG.get(seckillId);
        if (over != null && over) {
            // 本地售完标记为 true，标识商品已售完
            throw new BusinessException(SeckillCodeMsg.OUT_OF_STOCK_ERROR);
        }

        // 1. 根据秒杀id获取秒杀商品信息，判断秒杀id参数是否正确
        SeckillGoodVo vo = seckillGoodService.detailByCache(seckillId);
        if (vo == null) {
            throw new BusinessException(SeckillCodeMsg.OP_ERROR);
        }
        // 2. 判断当前时间是否在秒杀活动中
        Date now = new Date();
        if (now.compareTo(vo.getStartDate()) < 0) {
            throw new BusinessException(SeckillCodeMsg.NOT_START_ERROR);
        }
        // now: 10:00   end: 09:00
        if (now.compareTo(vo.getEndDate()) > 0) {
            throw new BusinessException(SeckillCodeMsg.END_ERROR);
        }
        // 3. 查看当前用户是否已经下过单
//        SeckillOrder seckillOrder = seckillOrderService.findByUserIdAndSeckillId(user.getId(), seckillId);
//        if (seckillOrder != null) {
//            throw new BusinessException(SeckillCodeMsg.REPATE_ERROR);
//        }
        Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent(SeckillRedisKey.SECKILL_USER_RECORD.join(seckillId + "", user.getId() + ""), "ok");
        if (ifAbsent == null || !ifAbsent) {
            // 如果当前key设置失败，就抛出用户重复下单异常
            throw new BusinessException(SeckillCodeMsg.REPATE_ERROR);
        }

        // 4. 判断秒杀库存是否足够
        Long remain = redisTemplate.opsForHash().increment(
                SeckillRedisKey.SECKILL_STOCK_COUNT_HASH.join(""),
                seckillId + "",
                -1
        );
        if (remain < 0) {
            // 标记当前秒杀商品已售完
            STOCK_COUNT_FLAG.put(seckillId, true);
            // 删除已经标记为用户重复下单的标识
            redisTemplate.delete(SeckillRedisKey.SECKILL_USER_RECORD.join(seckillId + "", user.getId() + ""));
            // 当库存不足时，直接标记该秒杀库存已售完
            throw new BusinessException(SeckillCodeMsg.OUT_OF_STOCK_ERROR);
        }
        // 5. 创建秒杀订单
        // 同步调用, 创建订单
        // String orderNo = orderInfoService.doSeckill(seckillId, user.getId());
        // 使用RocketMQ 发送创建订单的消息
        rocketMQTemplate.asyncSend(MQConstants.CREATE_ORDER_DEST, new CreateSeckillOrderMsg(uuid, seckillId, user.getId()), new MQLogSendCallback());
        return Result.success("正在努力抢购中!");
    }
}
