package com.seemygo.shop.cloud.service.impl;

import com.seemygo.shop.cloud.mapper.SeckillOrderMapper;
import com.seemygo.shop.cloud.service.ISeckillOrderService;
import com.seemygo.shop.cloud.domain.SeckillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillOrderServiceImpl implements ISeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Override
    public SeckillOrder findByUserIdAndSeckillId(Long userId, Long seckillId) {
        return seckillOrderMapper.selectByUserIdAndSeckillId(userId, seckillId);
    }

    @Override
    public void createSeckillOrder(Long seckillId, Long userId, String orderNo) {
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setOrderNo(orderNo);
        seckillOrder.setSeckillId(seckillId);
        seckillOrder.setUserId(userId);
        seckillOrderMapper.insert(seckillOrder);
    }
}
