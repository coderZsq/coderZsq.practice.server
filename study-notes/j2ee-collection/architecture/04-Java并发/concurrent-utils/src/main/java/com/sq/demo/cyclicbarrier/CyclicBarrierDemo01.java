package com.sq.demo.cyclicbarrier;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo01 {
    public static void main(String[] args) throws InterruptedException {
        // 1 定义一个指定任务的循环屏障
        final CyclicBarrier barrier = new CyclicBarrier(2);
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": 开始执行子任务1");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
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
                    TimeUnit.SECONDS.sleep(2);
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
        TimeUnit.SECONDS.sleep(5);
        t2.start();
    }
}
