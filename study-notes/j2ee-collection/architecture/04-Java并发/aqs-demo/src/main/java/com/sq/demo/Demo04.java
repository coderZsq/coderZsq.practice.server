package com.sq.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 共享锁
 */
public class Demo04 {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    // 读锁
    private static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(){
                @Override
                public void run() {
                    // 读锁, 是一个共享锁
                    readLock.lock();
                    try {
                        System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        readLock.unlock();
                    }
                }
            }.start();
        }

        new Thread(){
            @Override
            public void run() {
                // 写锁, 和读锁之间 是互斥锁
                writeLock.lock();
                try {
                    System.out.println("写锁");
                    System.out.println(Thread.currentThread().getId() + Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
                }
            }
        }.start();
    }
}
