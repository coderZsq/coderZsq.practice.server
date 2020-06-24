package com.sq.rocketmq._02_async;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class ConsumerDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个消费者对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group2");
        // 2. 设置NameServerAddr
        // 生产环境, 测试环境 NameServerAddr 不一样
        // 需要配置环境参数 export NAMESRV_ADDR=172.16.21.175:9876
        // consumer.setNamesrvAddr("172.16.21.175:9876");
        // 3. 先订阅消息主题
        consumer.subscribe("02_async", "*");
        // 4. 注册监听消息的处理
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("消费数据成功" + new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 5. 启动消费者
        consumer.start();
    }
}
