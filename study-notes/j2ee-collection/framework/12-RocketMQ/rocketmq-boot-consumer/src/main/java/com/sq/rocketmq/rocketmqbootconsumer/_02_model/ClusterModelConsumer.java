package com.sq.rocketmq.rocketmqbootconsumer._02_model;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        consumerGroup = "02_cluster_model",
        topic = "02_cluster_model",
        messageModel = MessageModel.CLUSTERING
)
public class ClusterModelConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("集群模式消费消息: " + message);
    }
}
