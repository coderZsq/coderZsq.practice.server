package com.sq.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程和线程之间的通信
 */
public class Demo03 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void put() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "往容器中存放数据资源处理");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getId() + "往容器中存放数据资源处理完成操作");
            condition.signalAll();
            condition.await();
        } finally {
            lock.unlock();
        }
    }

    public void get() throws InterruptedException {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "业务处理2");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getId() + "业务处理2完成操作");
            condition.signalAll();
            condition.await();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Demo03 demo03 = new Demo03();
        new Thread(() -> {
            for (;;) {
                try {
                    demo03.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (;;) {
                try {
                    demo03.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
