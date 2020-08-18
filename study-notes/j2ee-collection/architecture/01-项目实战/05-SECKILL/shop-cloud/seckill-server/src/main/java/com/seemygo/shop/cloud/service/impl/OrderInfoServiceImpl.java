package com.seemygo.shop.cloud.service.impl;

import com.seemygo.shop.cloud.mapper.OrderInfoMapper;
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
