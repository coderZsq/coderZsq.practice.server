package com.sq.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        // 1 创建一个线程池
        final ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(1, 2,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
                        new ThreadPoolExecutor.CallerRunsPolicy());

        final AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 6; i++) {
            poolExecutor.execute(()->{
                System.out.println(" task "+ atomicInteger.addAndGet(1));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("corepoolsize:" + poolExecutor.getPoolSize());
//        TimeUnit.SECONDS.sleep(5);
//        System.out.println("corepoolsize2:" + poolExecutor.getPoolSize());
        // 关闭线程池
        poolExecutor.shutdown();
        //配置: cpu:16核心,
        // IO密集: cpu是完全够用,  corepoolsize: cpu*2 32
        // CPU密集: 运算处理    corepoolsize <= cpu  16

    }
}
