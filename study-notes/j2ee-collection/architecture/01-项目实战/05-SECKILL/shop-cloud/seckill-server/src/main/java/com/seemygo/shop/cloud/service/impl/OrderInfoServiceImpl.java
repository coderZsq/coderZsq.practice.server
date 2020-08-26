package com.seemygo.shop.cloud.service.impl;

import com.seemygo.shop.cloud.mapper.OrderInfoMapper;
import com.seemygo.shop.cloud.mq.MQConstants;
import com.seemygo.shop.cloud.mq.MQLogSendCallback;
import com.seemygo.shop.cloud.redis.key.SeckillRedisKey;
import com.seemygo.shop.cloud.service.IOrderInfoService;
import com.seemygo.shop.cloud.service.ISeckillGoodService;
import com.seemygo.shop.cloud.service.ISeckillOrderService;
import com.seemygo.shop.cloud.vo.SeckillGoodVo;
import com.seemygo.shop.cloud.web.SeckillCodeMsg;
import com.seemygo.shop.cloud.domain.OrderInfo;
import com.seemygo.shop.cloud.domain.User;
import com.seemygo.shop.cloud.exception.BusinessException;
import com.seemygo.shop.cloud.util.IdGenerateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by wolfcode-lanxw
 */
@Service
@Slf4j
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private ISeckillGoodService seckillGoodService;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String doSeckill(Long seckillId, Long userId) {
        // 1. 扣库存
        int rows = seckillGoodService.decrStockCount(seckillId);
        if (rows <= 0) {
            throw new BusinessException(SeckillCodeMsg.OUT_OF_STOCK_ERROR);
        }

        // 2. 创建普通订单
        String orderNo = this.createOrder(seckillId, userId);
        try {
            // 3. 创建秒杀订单
            seckillOrderService.createSeckillOrder(seckillId, userId, orderNo);
        } catch (Exception e) {
            log.error("[秒杀操作] 创建秒杀订单失败", e);
            // 当重复下单时，重新抛出异常，触发事务回滚并且提示用户重复下单
            throw new BusinessException(SeckillCodeMsg.REPATE_ERROR);
        }
        return orderNo;
    }

    @Override
    public OrderInfo findById(String orderNo, Long userId) {
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderNo, userId);
        return orderInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkTimeout(String orderNo, Long seckillId) {
        // 1. 检查订单是否超时, 如果已经超时需要修改订单状态
        int ret = orderInfoMapper.updateTimeout(orderNo);
        if (ret == 0) {
            return;
        }
        // 2. 回补MySQL库存, MySQL库存+1
        seckillGoodService.incrStockCount(seckillId);
        // 回补Redis库存, 发布广播消息清除本地标识
        this.redisRollbackAndClearFlag(seckillId);
    }

    @Override
    public void seckillFailed(Long seckillId, Long userId) {
        // 回补Redis库存, 发布广播消息清除本地标识
        this.redisRollbackAndClearFlag(seckillId);
        // 删除用户已下单标识
        redisTemplate.delete(SeckillRedisKey.SECKILL_USER_RECORD.join(seckillId + "", userId + ""));
    }

    @Override
    public void updatePaySuccess(String orderNo) {
        orderInfoMapper.updatePaySuccess(orderNo);
    }

    private void redisRollbackAndClearFlag(Long seckillId) {
        // 3. 回补Redis库存
        SeckillGoodVo vo = seckillGoodService.detail(seckillId);
        redisTemplate.opsForHash().put(
                SeckillRedisKey.SECKILL_STOCK_COUNT_HASH.join(""),
                seckillId + "",
                vo.getStockCount() + ""
        );
        // 4. 发布广播消息, 清除本地售完标记
        rocketMQTemplate.asyncSend(MQConstants.CLEAR_STOCK_FLAG_DEST, seckillId, new MQLogSendCallback());
    }

    private String createOrder(Long seckillId, Long userId) {
        OrderInfo orderInfo = new OrderInfo();
        SeckillGoodVo vo = seckillGoodService.detail(seckillId);
        BeanUtils.copyProperties(vo, orderInfo);

        orderInfo.setCreateDate(new Date());
        orderInfo.setGoodCount(1);
        orderInfo.setUserId(userId);

        String orderNo = IdGenerateUtil.get().nextId() + "";
        orderInfo.setOrderNo(orderNo);
        orderInfoMapper.insert(orderInfo);
        return orderNo;
    }
}
