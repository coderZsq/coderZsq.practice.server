package com.seemygo.shop.cloud.web.controller;

import com.seemygo.shop.cloud.domain.OrderInfo;
import com.seemygo.shop.cloud.domain.SeckillOrder;
import com.seemygo.shop.cloud.domain.User;
import com.seemygo.shop.cloud.exception.BusinessException;
import com.seemygo.shop.cloud.redis.key.SeckillRedisKey;
import com.seemygo.shop.cloud.resp.Result;
import com.seemygo.shop.cloud.service.IOrderInfoService;
import com.seemygo.shop.cloud.service.ISeckillGoodService;
import com.seemygo.shop.cloud.service.ISeckillOrderService;
import com.seemygo.shop.cloud.util.CookieUtil;
import com.seemygo.shop.cloud.vo.SeckillGoodVo;
import com.seemygo.shop.cloud.web.SeckillCodeMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController extends BaseController {

    private final ISeckillGoodService seckillGoodService;
    private final IOrderInfoService orderInfoService;
    private final ISeckillOrderService seckillOrderService;
    private final StringRedisTemplate redisTemplate;

    public OrderController(ISeckillGoodService seckillGoodService, IOrderInfoService orderInfoService, ISeckillOrderService seckillOrderService, StringRedisTemplate redisTemplate) {
        this.seckillGoodService = seckillGoodService;
        this.orderInfoService = orderInfoService;
        this.seckillOrderService = seckillOrderService;
        this.redisTemplate = redisTemplate;
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
     *      100 * 50
     *      1237.0/s
     * @param seckillId
     * @param token
     * @return
     */
    @PostMapping
    public Result<String> doSeckill(Long seckillId, @CookieValue(CookieUtil.TOKEN_IN_COOKIE) String token) {
        User user = this.getCurrentUser(token);
        // 基础判断
        if (seckillId == null || user == null) {
            throw new BusinessException(SeckillCodeMsg.OP_ERROR);
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
        // SeckillOrder seckillOrder = seckillOrderService.findByUserIdAndSeckillId(user.getId(), seckillId);
        // if (seckillOrder != null) {
        //     throw new BusinessException(SeckillCodeMsg.REPATE_ERROR);
        // }
        Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent(SeckillRedisKey.SECKILL_USER_RECORD.join(seckillId + "", user.getId() + ""), "ok");
        if (ifAbsent == null || !ifAbsent) {
            // 如果当前key设置失败, 就抛出用户重复下单异常
            throw new BusinessException(SeckillCodeMsg.REPATE_ERROR);
        }

        // 4. 判断秒杀库存是否足够
        if (vo.getStockCount() <= 0) {
            // 删除已经标记为用户重复下单的标识
            redisTemplate.delete(SeckillRedisKey.SECKILL_USER_RECORD.join(seckillId + "", user.getId() + ""));
            // 当库存不足时，直接标记该秒杀库存已售完
            throw new BusinessException(SeckillCodeMsg.OUT_OF_STOCK_ERROR);
        }
        // 5. 创建秒杀订单
        String orderNo = orderInfoService.doSeckill(seckillId, user);
        return Result.success(orderNo);
    }


}
