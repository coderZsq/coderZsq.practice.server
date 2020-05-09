package com.coderZsq.redis._02_pipeline;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class PipeLineDemo {
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

    // 模拟发送写入10w个数据
    @Test
    public void testBasic() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            jedis.set("base:key" + i, "value");
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时时长: " + (end - start)); // 耗时时长: 17568
    }

    // 模拟发送写入10w个数据 pipeline
    @Test
    public void testPipeline() throws Exception {
        long start = System.currentTimeMillis();
        Pipeline pipelined = jedis.pipelined(); // 创建一个管道
        for (int i = 0; i < 100000; i++) {
            pipelined.set("pipe:key" + i, "value"); // 把需要执行的命令放管道中
        }
        // 同步执行命令
        pipelined.sync();
        long end = System.currentTimeMillis();
        System.out.println("耗时时长: " + (end - start)); // 耗时时长: 337
    }
}
