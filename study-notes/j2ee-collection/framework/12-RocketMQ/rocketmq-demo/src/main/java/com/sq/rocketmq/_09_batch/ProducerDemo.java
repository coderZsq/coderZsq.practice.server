package com.sq.rocketmq._09_batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;

public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("09_batch");
        // 3. 启动生产者
        producer.start();
        // 4. 创建消息Message
        // 5. 发送消息
        ArrayList<Message> messages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Message msg = new Message("09_batch", "hello, NBA".getBytes());
            // 可以给消息指定自定义参数
            msg.putUserProperty("name", "jack" + i);
            msg.putUserProperty("age", 23 + i + "");
            msg.putUserProperty("score", 80 + i + "");
            // 把消息放到list集合
            messages.add(msg);
        }
        // 不确定messages 是否超过4M 消息内容, 消息属性, 日志记录相关
        producer.send(messages);
        // 6. 关闭生产者
        producer.shutdown();
    }
}
