package com.sq.demo.semaphore;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo01 {
    public static void main(String[] args) {
        // 创建一个信号量, 拥有的许可数: 3
        final Semaphore semaphore = new Semaphore(3);
        // 微服务中的资源隔离, 信号量 iOS
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    // 获取到许可, 才可以执行
                    try {
                        semaphore.acquire(2);
                        test1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release(2); // 释放许可
                    }
                }
            }.start();
        }
    }

    /**
     * 当前方法 同时的并发数
     */
    // 业务方法 订单下单
    public static void test1() {
        System.out.println(new Date() + "SemaphoreDemo01.test1: 订单下单处理" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 业务方法2 订单支付
    public static void test2() {
        System.out.println("SemaphoreDemo01.test2: 订单下单处理" + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
