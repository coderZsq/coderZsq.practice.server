package com.sq.rocketmq._04_type;

import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class PullConsumer {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个消费者对象
        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("05_model_002");
        // 2. 设置NameServerAddr
        // 生产环境, 测试环境 NameServerAddr 不一样
        // 需要配置环境参数 export NAMESRV_ADDR=172.16.21.175:9876
        // consumer.setNamesrvAddr("172.16.21.175:9876");
        // 3. 先订阅消息主题
        consumer.subscribe("05_model", "*");
        consumer.setAutoCommit(false);
        // 5. 启动消费者
        consumer.start();
        while (true) {
            List<MessageExt> msgs = consumer.poll(); // 拉取间隔时间5s
            System.out.println(msgs);
            // 手动提交
            consumer.commitSync();
        }
    }
}
