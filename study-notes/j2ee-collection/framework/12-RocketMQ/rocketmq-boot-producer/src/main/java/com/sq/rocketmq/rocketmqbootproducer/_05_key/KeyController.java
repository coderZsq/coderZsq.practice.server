package com.sq.rocketmq.rocketmqbootproducer._05_key;

import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeyController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    private String topic = "05_boot_key";
    private String tag = "TagA";
    private String key = "9627";

    @RequestMapping("05_key")
    public String sendMsg(String msg) {
        org.springframework.messaging.Message<String> msg2 = MessageBuilder.withPayload(msg).setHeader(MessageConst.PROPERTY_KEYS, key).build();
        rocketMQTemplate.syncSend(topic, msg2);
        return "success";
    }
}
