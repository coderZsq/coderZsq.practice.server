package com.sq.rocketmq._02_async;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class ProducerDemo {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个生产者对象
        DefaultMQProducer producer = new DefaultMQProducer("async");
        // 2. 设置NameServer的地址
        // producer.setNamesrvAddr("172.16.21.175:9876");
        // 3. 启动生产者
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        // 4. 创建消息Message
        // 5. 发送消息
        // 创建一个计数器
        CountDownLatch2 countDownLatch = new CountDownLatch2(5);
        for (int i = 0; i < 5; i++) {
            Message msg = new Message("02_async", "hello, NBA".getBytes());
            try {
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println(Thread.currentThread().getName());
                        if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
                            System.out.println("消息发送成功" + JSON.toJSONString(sendResult));
                        }
                        countDownLatch.countDown();
                    }

                    // 异常处理
                    @Override
                    public void onException(Throwable e) {
                        e.printStackTrace();
                        countDownLatch.countDown();
                    }
                });
            } catch (MQClientException e) {
                // 补救措施 把对应的消息先保存到另一个地方 MySQL, 自己到时候重新触发 发送消息
                e.printStackTrace();
            } catch (RemotingException e) {
                // 补救措施
                e.printStackTrace();
            } catch (InterruptedException e) {
                // 补救措施
                e.printStackTrace();
            }
        }
        // 如果使用异步操作, 需要等待接收完所有的异步返回结果之后, 再去关闭主线程
        countDownLatch.await(); // 等待计数器归0

        // 6. 关闭生产者
        producer.shutdown();
    }
}
