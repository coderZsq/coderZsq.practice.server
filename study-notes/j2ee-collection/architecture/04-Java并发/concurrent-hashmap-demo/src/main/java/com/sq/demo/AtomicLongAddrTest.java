package com.sq.demo;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class AtomicLongAddrTest {

    public static void main(String[] args) throws Exception {
        test(1, 1000000);
        test(10, 1000000);
        test(100, 1000000);
    }

    static void test(int threadCount, int times) throws Exception {
        System.out.println("线程总数:" + threadCount + ", 自增次数:" + times);
        long start = System.currentTimeMillis();
        testAtomicLong(threadCount, times);
        System.out.println("AtomicLong 耗时:" + (System.currentTimeMillis() - start) + " ms");
        start = System.currentTimeMillis();
        testLongAddr(threadCount, times);
        System.out.println("LongAdder 耗时:" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("-----------------------------");
    }

    static void testAtomicLong(int threadCount, int times) throws Exception {
        // 创建一个原子类的Long
        final AtomicLong atomicLong = new AtomicLong();
        final ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < times; j++) {
                        // 在多线程环境进行+1
                        atomicLong.incrementAndGet();
                    }
                }
            }));
        }
        for (Thread thread : threads) {
            // 启动线程
            thread.start();
        }
        for (Thread thread : threads) {
            // 合并线程 阻塞 必须等到 thread 线程执行完成; 才会继续执行
            thread.join();
        }
        System.out.println("atomicLong values is: " + atomicLong.longValue());
    }

    // count size()
    static void testLongAddr(int threadCount, int times) throws Exception {
        final LongAdder longAdder = new LongAdder();
        final ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < times; j++) {
                        // 自增算法
                        longAdder.increment();
                    }
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("longAdder values is: " + longAdder.longValue());
    }
}
