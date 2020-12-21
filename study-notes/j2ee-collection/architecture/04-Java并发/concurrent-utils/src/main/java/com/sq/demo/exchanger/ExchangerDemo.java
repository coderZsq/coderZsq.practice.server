package com.sq.demo.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();

        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + ":准备交换数据");
                    final String result = exchanger.exchange("需要被交换的数据, 数据来源: " + Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName() + "获取到的数据" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T1");

        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + ":准备交换数据");
                    final String result = exchanger.exchange("需要被交换的数据, 数据来源: " + Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName() + "获取到的数据" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2");

        final Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + ":准备交换数据");
                    final String result = exchanger.exchange("需要被交换的数据, 数据来源: " + Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getName() + "获取到的数据" + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T3");

        t1.start();
        t2.start();
        t3.start();
    }
}
