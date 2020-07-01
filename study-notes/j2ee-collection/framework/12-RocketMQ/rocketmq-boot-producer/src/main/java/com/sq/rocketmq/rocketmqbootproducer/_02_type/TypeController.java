package com.sq.rocketmq.rocketmqbootproducer._02_type;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypeController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private String topic = "02_boot_type";

    @RequestMapping("02_type")
    public String sendMsg(String msg) {
        // 1 同步发送
        SendResult sendResult = rocketMQTemplate.syncSend(topic, msg);
        // 2 异步发送
        rocketMQTemplate.asyncSend(topic, msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步消息发送成功");
            }
            @Override
            public void onException(Throwable e) {
                System.out.println("异步消息发送失败");
            }
        });
        // 3 一次性消息
        rocketMQTemplate.sendOneWay(topic, msg);
        return "success";
    }
}
