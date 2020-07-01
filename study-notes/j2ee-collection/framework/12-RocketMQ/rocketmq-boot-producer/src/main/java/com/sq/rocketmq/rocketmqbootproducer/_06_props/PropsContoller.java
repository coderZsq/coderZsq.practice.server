package com.sq.rocketmq.rocketmqbootproducer._06_props;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

public class PropsContoller {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    private String topic = "06_boot_props";
    private String tag = "TagA";
    private String key = "9627";

    @RequestMapping("06_props")
    public String sendMsg(String msg) {
        HashMap map = new HashMap<>();
        // 通过Map添加自定义属性
        map.put("name", "rose");
        rocketMQTemplate.convertAndSend(topic, msg, map);
        return "success";
    }
}
