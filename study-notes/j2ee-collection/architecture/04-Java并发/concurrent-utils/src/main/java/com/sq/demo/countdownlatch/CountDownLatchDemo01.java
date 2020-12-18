package com.sq.demo.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo01 {
    public static void main(String[] args) throws InterruptedException {

        // 创建一个计数器对象, 并且赋值为2
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        /**
         * 创建两个线程
         */
        for (int i = 0; i < 2; i++) {
            new Thread("T"+ i) {
                @Override
                public void run() {
                    System.out.println("当前线程" + Thread.currentThread().getName() + "开始进行数据分析处理");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println("当前线程" + Thread.currentThread().getName() + "数据分析处理完毕");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown(); // 计数器-1
                    }
                }
            }.start();
        }
        // 阻塞当前线程, 当计数器为0的时候, 会唤醒当前线程
        countDownLatch.await();
        // 汇总处理之后的结果
        System.out.println("所有的任务线程都已经处理完毕, main 线程: 开始进行所有的数据汇总");
    }
}
