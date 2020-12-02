package com.sq.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁: synchronized, lock 当前获取到锁的线程, 可以继续获取当前的锁对象(获取锁对象的计数器state+1)
 */
public class Demo03 {
    private Lock lock = new ReentrantLock();

    // synchronized ==> this 当前对象
    public void test1() {
        lock.lock();
        try {
            System.out.println("Demo03.test1");
            test2();
        } finally {
            lock.unlock();
        }
    }

    public void test2() {
        lock.lock(); // state + 1
        try {
            System.out.println("Demo03.test2");
        } finally {
            lock.unlock(); // state - 1
        }
    }

    public static void main(String[] args) {
        Demo03 demo03 = new Demo03();
        new Thread(){
            @Override
            public void run() {
                demo03.test1();
            }
        }.start();
    }
}
