package com.sq.rocketmq._07_delay;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("07_delay");
        // 2. 设置NameServer的地址
        // producer.setNamesrvAddr("172.16.21.175:9876");
        // 3. 启动生产者
        producer.start();
        // 4. 创建消息Message
        for (int i = 0; i < 5; i++) {
            Message msg = new Message("07_delay", "Delay", i + "", "hello, NBA".getBytes());
            // 给小时设置延迟级别
            msg.setDelayTimeLevel(4);
            // 5. 发送消息
            SendResult result = producer.send(msg);
            System.out.println(JSON.toJSONString(result));
        }
        // 6. 关闭生产者
        producer.shutdown();
    }
}
