package com.seemygo.shop.cloud.service;

import com.seemygo.shop.cloud.domain.SeckillOrder;

public interface ISeckillOrderService {
    /**
     * 查询用户是否已经下过单
     *
     * @param userId
     * @param seckillId
     * @return
     */
    SeckillOrder findByUserIdAndSeckillId(Long userId, Long seckillId);

    /**
     * 创建秒杀订单
     *
     * @param seckillId
     * @param userId
     * @param orderNo
     */
    void createSeckillOrder(Long seckillId, Long userId, String orderNo);
}
