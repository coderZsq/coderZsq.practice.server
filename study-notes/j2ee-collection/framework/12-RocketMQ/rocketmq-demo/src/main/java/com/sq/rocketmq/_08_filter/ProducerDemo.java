package com.sq.rocketmq._08_filter;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("08_filter");
        // 3. 启动生产者
        producer.start();
        // 4. 创建消息Message
        for (int i = 0; i < 5; i++) {
            Message msg = new Message("01_hello", "hello, NBA".getBytes());
            // 可以给消息指定自定义参数
            msg.putUserProperty("name", "jack" + i);
            msg.putUserProperty("age", 23 + i + "");
            msg.putUserProperty("score", 80 + i + "");
            // 5. 发送消息
            SendResult result = producer.send(msg);
            System.out.println(JSON.toJSONString(result));
        }
        // 6. 关闭生产者
        producer.shutdown();
    }
}
