package com.sq.demo;

import java.util.concurrent.TimeUnit;

/**
 * 1 synchronized 不能以非阻塞的方式获取锁
 * 2 synchronized 不支持超时
 * 3 synchronized 不支持读写锁, 如果只有读操作, 那么就不需要阻塞, 多个线程可以共享执行, 如果有写操作, 读和写操作是互斥
 * 4 synchronized C++实现
 *
 * 李大师: 1.5 JUC的并发编程包 java.util.concurrent Java语言实现
 * AQS: AbstractQueuedSynchronizer 同步队列执行器
 */
public class Demo01 {
    public synchronized void test1() {
        System.out.println("只是读取数据, 不会修改数据");
        System.out.println("Demo01.test1");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test2() {
        // synchronized (this) {
            System.out.println("只是读取数据, 不会修改数据");
            System.out.println("Demo01.test2");
        // }
    }

    public void test3() {
        System.out.println("开始往共享变量写数据");
    }

    public static void main(String[] args) {
        final Demo01 demo01 = new Demo01();
        new Thread(){
            @Override
            public void run() {
                demo01.test1();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                demo01.test2();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                demo01.test3();
            }
        }.start();
    }
}
