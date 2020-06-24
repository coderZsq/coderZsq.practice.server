package com.sq.rocketmq._05_model;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("05_model");
        // 2. 设置NameServer的地址
        // producer.setNamesrvAddr("172.16.21.175:9876");
        // 3. 启动生产者
        producer.start();
        // 4. 创建消息Message
        MessageQueue queue = new MessageQueue("05_model", "localhost.localdomain", 3);
        // 5. 发送消息
        for (int i = 0; i < 5; i++) {
            Message msg = new Message("05_model", (i + "hello, NBA").getBytes());
            SendResult result = producer.send(msg, queue);
            System.out.println(JSON.toJSONString(result));
        }
        // 6. 关闭生产者
        producer.shutdown();
    }
}
