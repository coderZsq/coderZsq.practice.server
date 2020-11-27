package com.sq.demo;

import java.util.concurrent.TimeUnit;

/**
 * 可见性
 */
public class VisibleDemo {
    public static volatile boolean flag = true; // 可以保证数据可见性 在多个线程之间
    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        // 创建两个线程
        // 1个线程使用flag进行判断, 直到flag的值为true 停止循环
        new Thread() {
            @Override
            public void run() {
                while (flag) {
                    i++;
                }
            }
        }.start();
        TimeUnit.SECONDS.sleep(1);
        // 1个线程对flag的值进行改变
        new Thread() {
            @Override
            public void run() {
                flag = false; // 在线程中改变flag的值为false
            }
        }.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("i: " + i);
        System.out.println(flag);
    }
}
