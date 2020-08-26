package com.seemygo.shop.cloud.service;

import com.seemygo.shop.cloud.domain.OrderInfo;
import com.seemygo.shop.cloud.domain.User;

public interface IOrderInfoService {

    /**
     * 秒杀接口
     *
     * @param seckillId 秒杀商品id
     * @param userId 下单用户id
     * @return 创建的订单编号
     */
    String doSeckill(Long seckillId, Long userId);

    /**
     * 根据订单编号查询订单信息
     *
     * @param orderNo
     * @param userId
     * @return
     */
    OrderInfo findById(String orderNo, Long userId);

    /**
     * 检查订单是否超时, 如果超时需要进行回补, 清除本次售完标记
     * @param orderNo
     * @param seckillId
     */
    void checkTimeout(String orderNo, Long seckillId);

    /**
     * 当秒杀订单失败时进行回补Redis库存, 清除本地售完标记, 清除用户已下单标识
     * @param seckillId
     * @param userId
     */
    void seckillFailed(Long seckillId, Long userId);

    /**
     * 更新订单状态为支付成功
     * @param orderNo
     */
    void updatePaySuccess(String orderNo);
}
