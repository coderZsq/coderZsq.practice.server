package com.sq.demo;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    public static int count = 100;

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                count = count + 1;
            }
        };
        t1.start(); // 启动线程
        TimeUnit.SECONDS.sleep(1);
        System.out.println(count); // 101
        System.in.read();
    }
}
