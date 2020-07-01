package com.sq.rocketmq.rocketmqbootproducer._04_tag;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    private String topic = "04_boot_tag";
    private String tag = "TagA";

    @RequestMapping("04_tag")
    public String sendMsg(String msg) {
        rocketMQTemplate.syncSend(topic + ":" + tag, msg);
        return "success";
    }
}
