package com.coderZsq.redis._05_hyperloglog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Pipeline;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HyperLogLogDemo {
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
        jedis.pfadd("pf:login:20191206", "1");
        jedis.pfadd("pf:login:20191206", "3");
        jedis.pfadd("pf:login:20191206", "5");
        jedis.pfadd("pf:login:20191206", "5");
        jedis.pfadd("pf:login:20191206", "8");
    }

    @Test
    public void testLog() throws Exception {
        Pipeline pipelined = jedis.pipelined();
        for (int i = 0; i < 10000000; i++) {
            pipelined.pfadd("pf:user:login:20191207", "" + i);
        }
        pipelined.sync();
        System.out.println(jedis.pfcount("pf:user:login:20191207"));
    }

    @Test
    public void initData2() throws Exception {
        // 添加登录过的用户 添加一万个用户
        Pipeline pipelined = jedis.pipelined();
        for (int i = 0; i < 5000000; i++) {
            pipelined.set("key" + i, "value" + i);
        }
        pipelined.sync();
    }

    @Test
    public void testFailover() throws Exception {
        String masterName = "mymaster";
        Set<String> sentinels = new HashSet<>();
        sentinels.add("172.16.21.175:26379");
        sentinels.add("172.16.21.175:26380");
        sentinels.add("172.16.21.175:26381");
        JedisSentinelPool pool = new JedisSentinelPool(masterName, sentinels);
        while (true) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.incr("age");
                System.out.println("age = " + jedis.get("age"));
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }
}
