package com.coderZsq.redis._03_pubsub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class SubscriberDemo {
    private Jedis jedis = null; // 客户端连接对象

    @Before
    public void init() throws Exception {
        jedis = new Jedis("172.16.21.175", 6379);
    }

    @After
    public void close() throws Exception {
        if (jedis != null) {
            jedis.close();
        }
    }

    // 订阅channel 消费消息
    @Test
    public void produce() throws Exception {
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("channel = " + channel); // 哪个频道
                System.out.println("message = " + message); // 消息内容
            }
        };
        jedis.subscribe(jedisPubSub,"9527");
    }
}
