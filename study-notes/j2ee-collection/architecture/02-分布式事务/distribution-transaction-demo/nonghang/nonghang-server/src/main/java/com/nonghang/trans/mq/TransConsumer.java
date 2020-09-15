package com.nonghang.trans.mq;

import com.nonghang.trans.service.ITransService;
import lombok.AllArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RocketMQMessageListener(
        topic = "nonghang-trans",
        consumerGroup = "consumer007"
)
@AllArgsConstructor
public class TransConsumer implements RocketMQListener<Map> {

    private ITransService trans;
    //监听消息
    public void onMessage(Map map) {
        Integer inId= (Integer) map.get("inId");
        Integer amount= (Integer) map.get("amount");
        String transId= (String) map.get("transId");
        trans.tranIn(inId,amount,transId);
//        throw new RuntimeException("模拟ack确认网络异常");
    }
}