package com.sq.rocketmq.rocketmqbootproducer._01_hello;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private String topic = "01_boot_hello";

    // 需求: 把接收的msg消息发送到mq的topic中
    @RequestMapping("01_hello")
    public String hello(String msg) throws Exception {
        System.out.println("msg = " + msg);
        // 发送消息
        SendResult sendResult = rocketMQTemplate.syncSend(topic, msg);
        return sendResult.getSendStatus().toString();
    }
}
