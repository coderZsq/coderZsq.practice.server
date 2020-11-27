package com.sq.demo.sync;

public class Demo {
    public static volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    synchronized (Demo.class) {
                        count++;
                    }
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    synchronized (Demo.class) {
                        count++;
                    }
                }
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("count = " + count);
    }
}
