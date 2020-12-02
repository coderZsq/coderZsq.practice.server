package com.sq.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock api的基本使用
 */
public class Demo05 {
    private static Lock lock = new ReentrantLock();

    public void test1() {
        if (lock.tryLock()) {
            try {
                System.out.println("Demo05.test1");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public void test2() {
        if (lock.tryLock()) {
            try {
                System.out.println("Demo05.test2");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("由于没有获取到锁, 执行其他的操作");
        }
    }
    public static void main(String[] args) {
        final Demo05 demo05 = new Demo05();
        new Thread(){
            @Override
            public void run() {
                demo05.test1();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                demo05.test2();
            }
        }.start();
    }
}
