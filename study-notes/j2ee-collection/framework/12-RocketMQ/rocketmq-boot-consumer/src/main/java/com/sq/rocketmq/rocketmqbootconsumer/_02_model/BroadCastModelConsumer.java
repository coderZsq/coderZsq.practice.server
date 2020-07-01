package com.sq.rocketmq.rocketmqbootconsumer._02_model;

import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

// 对于广播模式, 实时消费消息, 在广播模式消费者启动之前的消息, 是接收不到
// 2 对于广播模式, 发送失败的消息不会重试
@Component
@RocketMQMessageListener(
        consumerGroup = "02_broadcasting_model",
        topic = "02_broadcasting_model",
        messageModel = MessageModel.BROADCASTING
)
public class BroadCastModelConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("广播模式消费消息: " + message);
    }
}
