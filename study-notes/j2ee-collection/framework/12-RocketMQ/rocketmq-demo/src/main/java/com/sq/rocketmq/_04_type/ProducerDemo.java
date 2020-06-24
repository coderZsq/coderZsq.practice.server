package com.sq.rocketmq._04_type;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("04_type");
        // 2. 设置NameServer的地址
        // 3. 启动生产者
        producer.start();
        // 4. 创建消息Message
        // 5. 发送消息
        for (int i = 0; i < 5; i++) {
            // Tag: 消息标签, 用来进行消息分类 支付消息 --> wx支付, zfb支付
            // Key: 业务消息 订单消息: key: 订单id/订单号
            Message msg = new Message("04_type", "TagB", i + "", (i + "hello, pullMsg").getBytes());
            SendResult result = producer.send(msg);
            System.out.println(JSON.toJSONString(result));
        }
        // 6. 关闭生产者
        producer.shutdown();
    }
}
