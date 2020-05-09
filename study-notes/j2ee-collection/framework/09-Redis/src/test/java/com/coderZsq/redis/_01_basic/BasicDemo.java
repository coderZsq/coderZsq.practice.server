package com.coderZsq.redis._01_basic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class BasicDemo {
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

    @Test
    public void test1() throws Exception {
        String val = jedis.set("hello", "java");
        System.out.println("val = " + val);
    }
}
