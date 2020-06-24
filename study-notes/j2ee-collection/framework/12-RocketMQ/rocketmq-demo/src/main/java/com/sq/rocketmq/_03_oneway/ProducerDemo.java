package com.sq.rocketmq._03_oneway;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("03_oneway");
        // 2. 设置NameServer的地址
        // 3. 启动生产者
        producer.start();
        // 4. 创建消息Message
        // 5. 发送消息
        for (int i = 0; i < 5; i++) {
            // Tag: 消息标签, 用来进行消息分类 支付消息 --> wx支付, zfb支付
            // Key: 业务消息 订单消息: key: 订单id/订单号
            Message msg = new Message("03_oneway", "TagB", i + "", "hello, NBA".getBytes());
            producer.sendOneway(msg); // 日志, 大数据应用
        }
        // 6. 关闭生产者
        producer.shutdown();
    }
}
