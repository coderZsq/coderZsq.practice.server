package com.sq.rocketmq.rocketmqbootconsumer._01_hello;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

// 消费者
// 1 必须是容器组件
@Component
@RocketMQMessageListener(
        consumerGroup = "test-8567",
        topic = "01_boot_hello",
        messageModel = MessageModel.CLUSTERING
)
public class HelloConsumer implements RocketMQListener<MessageExt> {
    // 回调方法
    @Override
    public void onMessage(MessageExt ext) {
        System.out.println("接收到的消息 message = " + new String(ext.getBody()));
        System.out.println("ext = " + JSON.toJSONString(ext));
    }
}
