package com.seemygo.shop.cloud.mq;

/**
 * 秒杀mq消息的常量
 */
public interface MQConstants {
    /**
     * 秒杀订单的主题
     */
    String SECKILL_ORDER_TOPIC = "SECKILL_ORDER_TOPIC";

    /**
     * 创建订单标签
     */
    String CREATE_ORDER_TAG = "CREATE_ORDER_TAG";

    /**
     * 创建订单消息目的地
     */
    String CREATE_ORDER_DEST = SECKILL_ORDER_TOPIC + ":" + CREATE_ORDER_TAG;

    /**
     * 创建订单的消费者组
     */
    String CREATE_ORDER_CONSUMER_GROUP = "CREATE_ORDER_CONSUMER_GROUP";
}
