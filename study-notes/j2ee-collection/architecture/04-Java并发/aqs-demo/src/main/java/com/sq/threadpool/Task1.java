package com.sq.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Task1 implements Runnable {
    private Lock lock ;
    // 完成多个线程之间的同步
    private Condition condition;
    public Task1(Lock lock,Condition condition) {
        this.lock = lock;
        this.condition =condition;
    }

    @Override
    public void run() {
        lock.lock();
        try{
            System.out.println("start ......Task1.run");
            //①
            try {
                condition.await(); //阻塞当前线程, 并且释放锁对象, 并且唤醒下一个线程
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end ......Task1.run");
        }finally {
            lock.unlock();
        }
    }
}
