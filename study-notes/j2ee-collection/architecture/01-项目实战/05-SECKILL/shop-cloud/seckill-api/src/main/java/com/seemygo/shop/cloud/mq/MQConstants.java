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

    /**
     * 秒杀成功标签
     */
    String SECKILL_SUCCESS_TAG = "SECKILL_SUCCESS_TAG";

    /**
     * 秒杀成功消息目的地
     */
    String SECKILL_SUCCESS_DEST = SECKILL_ORDER_TOPIC + ":" + SECKILL_SUCCESS_TAG;

    /**
     * 秒杀成功的消费者组
     */
    String SECKILL_SUCCESS_CONSUMER_GROUP = "SECKILL_SUCCESS_CONSUMER_GROUP";

    /**
     * 秒杀失败标签
     */
    String SECKILL_FAILED_TAG = "SECKILL_FAILED_TAG";

    /**
     * 秒杀失败消息目的地
     */
    String SECKILL_FAILED_DEST = SECKILL_ORDER_TOPIC + ":" + SECKILL_FAILED_TAG;

    /**
     * 秒杀失败的消费者组
     */
    String SECKILL_FAILED_CONSUMER_GROUP = "SECKILL_FAILED_CONSUMER_GROUP";

    /**
     * 延迟订单标签
     */
    String DELAY_ORDER_TAG = "DELAY_ORDER_TAG";

    /**
     * 延迟订单消息目的地
     */
    String DELAY_ORDER_DEST = SECKILL_ORDER_TOPIC + ":" + DELAY_ORDER_TAG;

    /**
     * 延迟订单的消费者组
     */
    String DELAY_ORDER_CONSUMER_GROUP = "DELAY_ORDER_CONSUMER_GROUP";

    /**
     * 清除本地标识标签
     */
    String CLEAR_STOCK_FLAG_TAG = "CLEAR_STOCK_FLAG_TAG";

    /**
     * 清除本地标识消息目的地
     */
    String CLEAR_STOCK_FLAG_DEST = SECKILL_ORDER_TOPIC + ":" + CLEAR_STOCK_FLAG_TAG;

    /**
     * 清除本地标识的消费者组
     */
    String CLEAR_STOCK_FLAG_CONSUMER_GROUP = "CLEAR_STOCK_FLAG_CONSUMER_GROUP";
}
