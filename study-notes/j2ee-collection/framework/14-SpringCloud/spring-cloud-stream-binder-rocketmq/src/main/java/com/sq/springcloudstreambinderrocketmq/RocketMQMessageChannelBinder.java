package com.sq.springcloudstreambinderrocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.messaging.*;
import org.springframework.messaging.support.GenericMessage;

import java.util.List;

public class RocketMQMessageChannelBinder implements Binder<MessageChannel, ConsumerProperties, ProducerProperties> {

    private String TEST_GROUP = "test_group";
    private String NAMESERVER_ADDR = "172.16.21.175:9876";
    private String TEST_TOPIC = "TEST_TOPIC";

    // 消费数据
    @Override
    public Binding<MessageChannel> bindConsumer(String name, String group, MessageChannel inboundBind, ConsumerProperties consumerProperties) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(TEST_GROUP);
        consumer.setNamesrvAddr(NAMESERVER_ADDR);
        try {
            consumer.subscribe(TEST_TOPIC, "*");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    byte[] bytes = msg.getBody();
                    inboundBind.send(new GenericMessage(bytes));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return () -> { // 释放资源
            consumer.shutdown();
        };
    }

    // 构建生产api
    @Override
    public Binding<MessageChannel> bindProducer(String name, MessageChannel outboundBind, ProducerProperties producerProperties) {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup(TEST_GROUP);
        producer.setNamesrvAddr(NAMESERVER_ADDR);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        SubscribableChannel channel = (SubscribableChannel) outboundBind;
        channel.subscribe(new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                byte[] msg = (byte[]) message.getPayload();
                try {
                    org.apache.rocketmq.common.message.Message mqMessage = new org.apache.rocketmq.common.message.Message();
                    mqMessage.setTopic(TEST_TOPIC);
                    mqMessage.setBody(msg);
                    SendResult sendResult = producer.send(mqMessage);
                    System.out.println("消息发送: sendResult = " + sendResult);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        return () -> { // 释放资源
            producer.shutdown();
        };
    }
}
