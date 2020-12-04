package com.sq.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockDemo 锁的编程方式
 */
public class Demo02 {

    // 创建一个Lock锁对象
    private static Lock lock = new ReentrantLock();

    public void test1() {
        lock.lock(); // 线程一: 没有获取到锁
        try {
            System.out.println("模拟并发处理资源操作");
            System.out.println(Thread.currentThread().getId());
            throw new RuntimeException("模拟网络异常");
        } finally {
            // 线程二: lock.lock方法成功获取到了锁
            // 前提条件, 必须获取到锁
            lock.unlock(); // 释放锁 保证可以完成释放
        }
    }

    public static void main(String[] args) {
        Demo02 demo02 = new Demo02();
        demo02.test1();
    }
}
