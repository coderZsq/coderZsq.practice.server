package com.coderZsq;

// 继承Thread, 重写run方法
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("开启了新线程: " + Thread.currentThread());
    }
}
