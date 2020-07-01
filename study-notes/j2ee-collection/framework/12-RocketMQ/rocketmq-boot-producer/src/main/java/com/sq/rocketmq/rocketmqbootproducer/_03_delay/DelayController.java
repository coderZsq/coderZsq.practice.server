package com.sq.rocketmq.rocketmqbootproducer._03_delay;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DelayController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    private String topic = "03_boot_delay";

    // 需求: 把接收的msg消息发送到mq的topic中
    @RequestMapping("03_delay")
    public String hello(String msg) throws Exception {
        // 设置延迟消息
        // 方式一: 直接使用原生的api
        DefaultMQProducer producer = rocketMQTemplate.getProducer();
        Message message = new Message(topic, "TagA", "9527", msg.getBytes());
        message.setDelayTimeLevel(3);
        // 在实际工作中, 确保消息可靠性, 捕获对应的异常
        producer.send(message);
        // 方式二: 找可以使用延迟级别的API
        org.springframework.messaging.Message<String> msg2 = MessageBuilder.withPayload(msg).build();
        rocketMQTemplate.syncSend(topic, msg2, 3000, 3);
        return "success";
    }
}
