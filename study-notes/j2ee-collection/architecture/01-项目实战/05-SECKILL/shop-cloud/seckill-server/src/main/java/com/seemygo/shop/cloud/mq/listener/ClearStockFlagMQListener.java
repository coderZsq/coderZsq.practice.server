package com.seemygo.shop.cloud.mq.listener;

import com.seemygo.shop.cloud.mq.MQConstants;
import com.seemygo.shop.cloud.web.controller.OrderController;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RocketMQMessageListener(
        topic = MQConstants.SECKILL_ORDER_TOPIC,
        selectorExpression = MQConstants.CLEAR_STOCK_FLAG_TAG,
        messageModel = MessageModel.BROADCASTING,
        consumerGroup = MQConstants.CLEAR_STOCK_FLAG_CONSUMER_GROUP
)
public class ClearStockFlagMQListener implements RocketMQListener<Long> {

    @Override
    public void onMessage(Long seckillId) {
        log.info("[清除本地库存标记消息] 收到清除本地库存标记消息: {}", seckillId);
        // 清除当前服务器节点的本地售完标记
        OrderController.STOCK_COUNT_FLAG.put(seckillId, false);
    }
}
