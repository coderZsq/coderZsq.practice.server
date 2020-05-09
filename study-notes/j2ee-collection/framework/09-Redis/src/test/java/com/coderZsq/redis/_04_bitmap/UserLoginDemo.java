package com.coderZsq.redis._04_bitmap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

// 记录每天用户的登录数量
public class UserLoginDemo {
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
    public void initData() throws Exception {
        // 初始化用户登录数据
        jedis.setbit("user:login:20191206", 1, "1");
        jedis.setbit("user:login:20191206", 3, "1");
        jedis.setbit("user:login:20191206", 5, "1");
        jedis.setbit("user:login:20191206", 5, "1");
        jedis.setbit("user:login:20191206", 8, "1");

        jedis.setbit("user:login:20191207", 11, "1");
        jedis.setbit("user:login:20191207", 3, "1");
        jedis.setbit("user:login:20191207", 5, "1");
        jedis.setbit("user:login:20191207", 18, "1");

        jedis.setbit("user:login:20191208", 21, "1");
        jedis.setbit("user:login:20191208", 13, "1");
        jedis.setbit("user:login:20191208", 5, "1");
        jedis.setbit("user:login:20191208", 28, "1");
    }

    @Test
    public void resultData() throws Exception {
        // 1 20191206 有多少用户登录
        System.out.println(jedis.bitcount("user:login:20191206")); // 4
        // 2 最近三天 20191206 20191207 20191208 有多少用户登录
        jedis.bitop(BitOP.OR, "user:login:last31", "user:login:20191206", "user:login:20191207", "user:login:20191208");
        System.out.println(jedis.bitcount("user:login:last31")); // 9
        // 3 统计连续登录三天的用户
        jedis.bitop(BitOP.AND, "user:login:last32", "user:login:20191206", "user:login:20191207", "user:login:20191208");
        System.out.println(jedis.bitcount("user:login:last32")); // 1 --> 5
        System.out.println("===========");
        System.out.println(jedis.bitpos("user:login:last32", true));
    }

    // 比较一下用set 和bitmap直接的一个存储量的差别
    @Test
    public void testSet() throws Exception {
        Pipeline pipelined = jedis.pipelined();
        for (int i = 0; i < 1000000; i++) {
            pipelined.sadd("set:user:login:20191206", "user:" + i);
        }
        pipelined.sync();
    }

    // 比较一下用set 和bitmap直接的一个存储量的差别
    @Test
    public void testBitMap() throws Exception {
        Pipeline pipelined = jedis.pipelined();
        for (int i = 0; i < 1000000; i++) {
            pipelined.setbit("bit:user:login:20191206", i, true);
        }
        pipelined.sync();
    }
}
