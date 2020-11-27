package com.sq.demo.jmm;

import java.util.concurrent.TimeUnit;

/**
 * 并发的原子性
 */
public class AtomicDemo {
    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10; j++) {
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    for (int k = 0; k < 1000; k++) {
                        synchronized ("abc") {
                            i++; // ==> i = i + 1
                        }
                    }
                }
            };
            t1.start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println(i); // i的值?
    }
}
