package com.coderZsq.redis._07_redissentinel;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisSentinelDemo {
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
