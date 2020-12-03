package com.sq.aqs;

import java.util.concurrent.TimeUnit;

/**
 * 线程和线程之间的通信
 */
public class Demo02 {
    public void put() throws InterruptedException {
        synchronized (this) {
            System.out.println(Thread.currentThread().getId() + "往容器中存放数据资源处理");
            TimeUnit.SECONDS.sleep(1);
            this.notifyAll(); // 唤醒处于等待的线程
            // 需要处于等待状态
            this.wait();
        }
    }

    public void get() throws InterruptedException {
        synchronized (this) {
            System.out.println(Thread.currentThread().getId() +  "从容器中获取对应的资源");
            TimeUnit.SECONDS.sleep(1);
            this.notifyAll(); // 唤醒处于等待的线程
            this.wait();
        }
    }

    public static void main(String[] args) {
        Demo02 demo02 = new Demo02();
        new Thread(() -> {
            for (;;) {
                try {
                    demo02.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (;;) {
                try {
                    demo02.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
