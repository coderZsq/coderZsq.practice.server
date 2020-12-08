package com.sq.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Task2 implements Runnable {
    //  task1 和task2 需要使用同一个锁资源对象
    private Lock lock ;
    // 完成多个线程之间的同步
    private Condition condition;
    public Task2(Lock lock,Condition condition) {
        this.lock = lock;
        this.condition =condition;
    }

    @Override
    public void run() {
        lock.lock();
        try{
                System.out.println("start ......Task2.run");
                // ②
                try {
                    condition.signal(); //唤醒线程  task1 继续执行, 并不会释放锁对象
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            System.out.println("end ......Task2.run");
        }finally {
            lock.unlock();
        }

    }
}
