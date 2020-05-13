package com.coderZsq.redis._08_pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisPoolDemo {
    @Test
    public void testPool() throws Exception {
        // 1 创建连接池对象
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMinIdle(10);
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxWaitMillis(10000);
        String host = "172.16.21.175";
        int port = 6379;
        JedisPool jedisPool = new JedisPool(poolConfig, host, port);
        // 2. 获取连接对象
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("bb", "1");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
