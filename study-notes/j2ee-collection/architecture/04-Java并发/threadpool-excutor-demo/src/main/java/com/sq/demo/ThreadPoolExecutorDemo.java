package com.sq.demo;

import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         *  corePoolSize: 核心线程数量
         *  maximumPoolSize: 最大线程数量 包括核心线程数量
         *  keepAliveTime: 最长有效时间
         *  unit: 时间单位
         *  workQueue: 等待阻塞队列, 当核心线程数都在执行任务处理的时候, 会把需要处理的任务放到阻塞队列
         *  threadFactory: 创建线程工厂, 创建需要运行任务的线程
         *  handler 线程拒绝策略: 当阻塞队列满了, 并且最大核心线程数都在运行任务, 新来的任务就会交个拒绝策略进行处理
         *  任务: IO密集型 (处理时间, 吞吐量), CPU密集型 ==> 线程数量:  不要超过cpu核心数
         */
        // 创建一个线程
        // ThreadPoolExecutor 建议 Executors 帮助类, 通过少量参数帮助我们创建多线程方案
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(
                0,
                1,
                5L,
                TimeUnit.SECONDS,
                // SynchronousQueue 没有容器, 生成消息 --> 阻塞 --> 消费消息 --> 唤醒
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        return t;
                    }
                },
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 15; i++) {
            TimeUnit.SECONDS.sleep(1);
            final int index = i;
            // if (i == 10) {
            //     executor.shutdownNow();
            // }
            // submit: 启动的线程可以有返回值, execute来说, 没有返回
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ": 正在执行任务:" + index);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        /**
         * 1 源码分析:
         * execute方法: 提交任务, 做一些基本的判断
         * addWorker方法: 添加任务处理 true, 否则: false
         * new Worker(firstTask): 创建一个work任务, 实现Runnable接口, run方法
         * runWorker: 方法, 运行任务
         * getTask: 从阻塞队列中拉取任务
         */
        System.out.println(123);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("活动的线程数量1:" + executor.getPoolSize());
        TimeUnit.SECONDS.sleep(60);
        System.out.println("活动的线程数量2:" + executor.getPoolSize());
        executor.shutdown();
    }
}
