package com.sq.impl.v1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class LockDemo {
    public Lock lock = new MyReentrantLock();

    public void test1() {
        lock.lock();
        try {
            System.out.println("test1... begin...");
            System.out.println("LockDemo.test1");
            // 调用test2
            test2();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test1... end...");
        } finally {
            lock.unlock();
        }
    }

    public void test2() {
        lock.lock();
        try {
            System.out.println("test2... begin...");
            System.out.println("LockDemo.test2");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test2... end...");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final LockDemo lockDemo = new LockDemo();
        final Thread t1 = new Thread(() -> {
            lockDemo.test1();
        });
        final Thread t2 = new Thread(() -> {
            lockDemo.test2();
        });
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
