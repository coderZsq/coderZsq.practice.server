package com.sq.rocketmq._05_model;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个消费者对象
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("05_model");
        // 消息模式设置为集群
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.subscribe("05_model", "*");
        // 最后的位置, 可以消费到以前的数据
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setConsumeTimestamp("20200623210000");
        // 4. 注册监听消息的处理
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("消费Consumer001 数据成功" + new String(msg.getBody()) + "msg.getQueueId():" + msg.getQueueId());
                }
                // 也会改变消费者偏移量, 但是数据会放到重试队列
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        System.out.println("消费Consumer001启动成功");
        // 5. 启动消费者
        consumer.start();
    }
}
