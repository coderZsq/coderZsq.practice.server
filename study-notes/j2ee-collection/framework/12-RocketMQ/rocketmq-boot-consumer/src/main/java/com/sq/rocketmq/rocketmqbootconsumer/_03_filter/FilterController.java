package com.sq.rocketmq.rocketmqbootconsumer._03_filter;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        consumerGroup = "03_filter",
        topic = "03_cluster",
        messageModel = MessageModel.CLUSTERING,
        selectorType = SelectorType.SQL92,
        selectorExpression = " name ='rose' or age > 18 "
)
public class FilterController implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("集群模式消费信息: " + message);
    }
}
