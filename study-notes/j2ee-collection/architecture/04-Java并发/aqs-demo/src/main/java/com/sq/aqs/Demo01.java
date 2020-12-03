package com.sq.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo01 {
    public static void main(String[] args) throws InterruptedException {
        // 同步锁 可重入锁
        final Lock lock = new ReentrantLock();

        final Thread t1 = new Thread(() -> {
            for (;;) {
                try {
                    lock.lockInterruptibly(); // 没有获取到锁, 会进入到线程阻塞状态
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println("开始业务执行");
                } finally {
                    lock.unlock(); // 释放锁对象
                }
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        // 发送线程中断信号
        t1.interrupt();
    }
}
