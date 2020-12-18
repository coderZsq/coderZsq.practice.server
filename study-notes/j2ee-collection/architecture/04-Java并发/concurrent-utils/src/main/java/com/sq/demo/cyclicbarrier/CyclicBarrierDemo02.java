package com.sq.demo.cyclicbarrier;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierDemo02 {
    public static void main(String[] args) throws InterruptedException {
        // 1 定义一个指定任务的循环屏障
        final CyclicBarrier barrier = new CyclicBarrier(2, new TimeTask(new Date()));

        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": 开始执行子任务1");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    // 设置超时时间
                    // barrier.await(2, TimeUnit.SECONDS);
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date() + "所有线程的子任务1 都已经执行," + Thread.currentThread().getName() + ": 开始执行子任务2");
            }
        }, "T1");
        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": 开始执行子任务1");
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(666);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    // await方法, 阻塞
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(new Date() + "所有线程的子任务1 都已经执行," + Thread.currentThread().getName() + ": 开始执行子任务2");
            }
        }, "T2");
        t1.start();
        // TimeUnit.SECONDS.sleep(5);
        t2.start();
        while (true) {
            System.out.println("getNumberWaiting = " + barrier.getNumberWaiting());
            System.out.println("getParties = " + barrier.getParties());
            System.out.println("======================================");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    static class TimeTask implements Runnable {
        Date start = null;

        public TimeTask(Date start) {
            this.start = start;
        }

        @Override
        public void run() {
            System.out.println("总共任务执行时间(ms): " + (System.currentTimeMillis() - start.getTime()));
        }
    }
}
