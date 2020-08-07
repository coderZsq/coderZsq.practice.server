package com.seemygo.shop.cloud.service;

import com.seemygo.shop.cloud.domain.OrderInfo;
import com.seemygo.shop.cloud.domain.User;

public interface IOrderInfoService {

    /**
     * 秒杀接口
     *
     * @param seckillId 秒杀商品id
     * @param user 下单用户
     * @return 创建的订单编号
     */
    String doSeckill(Long seckillId, User user);

    /**
     * 根据订单编号查询订单信息
     *
     * @param orderNo
     * @param userId
     * @return
     */
    OrderInfo findById(String orderNo, Long userId);
}
