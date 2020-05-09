package com.coderZsq.redis._03_pubsub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

public class PublisherDemo {
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

    // 生产消息
    @Test
    public void produce() throws Exception {
        for (int i = 0; i < 100; i++) {
            jedis.publish("9527", "hello" + i);
            TimeUnit.MICROSECONDS.sleep(10);
        }
    }
}
