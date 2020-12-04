package com.sq.impl.v1;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 自定义一个可重入锁
 */
public class MyReentrantLock implements Lock {
    private static Unsafe unsafe = UnsafeUtil.getInstance();

    /**
     * 变量state在内存中的一个偏移量
     */
    private static long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(MyReentrantLock.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步锁的状态值
     */
    private int state = 0;

    /**
     * 获取锁的线程对象
     */
    private Thread holdThread;

    /**
     * 等待的线程队列
     */
    private ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue<>();

    @Override
    public void lock() {
        acquire();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        release();
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * 获取锁的方法
     */
    public void acquire() {
        while (!tryAcquire()) { // 没有获取到锁
            final Thread currentThread = Thread.currentThread();
            // 1 把当前线程添加到waiter等待队列
            waiters.add(currentThread);
            // 2 让当前线程处于阻塞状态
            System.out.println("线程进入阻塞状态 " + currentThread.getName());
            LockSupport.park(); // 阻塞当前线程, 等待LockSupport, unpark(thread); 操作唤醒线程
            System.out.println(currentThread.getName() + "唤醒之后, 继续执行...");
        }
    }

    /**
     * 尝试获取锁
     * @return true: 获取成功, false:获取失败
     */
    public boolean tryAcquire() {
        // 第一次获取对象
        if (!hasWaiterThread() && compareAndSwap(0, 1)) {
            holdThread = Thread.currentThread();
            return true;
        }
        // 如果是可重入锁
        if (holdThread == Thread.currentThread()) {
            // compareAndSwap(state, state + 1);
            // 虽然不是原子性操作, 但是只有单线程的情况才会执行该业务代码
            // state = 3 代表同一个线程获取了3次锁对象
            state = state + 1;
            return true;
        }
        return false;
    }

    /**
     * 释放锁对象
     */
    public void release() {
        if (tryRelease()) {
            holdThread = null;
            if (!waiters.isEmpty() && waiters.peek() != null) {
                // 获取等待队列中的第一个线程
                Thread thread = waiters.poll();
                // 唤醒线程
                System.out.println("thread.getName() = " + thread.getName());
                LockSupport.unpark(thread);
            }
        }
    }

    /**
     * 是否释放成功, 如果释放成功, 返回true, 否则返回false
     * @return
     */
    public boolean tryRelease() {
        final Thread currentThread = Thread.currentThread();
        if (currentThread != holdThread) {
            System.out.println("holdThread = " + holdThread);
            throw new RuntimeException("释放锁异常..");
        }
        state = state - 1;
        if (state == 0) {
            holdThread = null;
            return true;
        }
        return false;
    }

    /**
     * 比较 并且交换
     * @param expect 需要比较的值
     * @param update 修改后的值
     * @return
     */
    public boolean compareAndSwap(int expect, int update) {
        // 原子性的操作
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    /**
     * 是否有等待的线程
     * @return
     */
    public boolean hasWaiterThread() {
        if (!waiters.isEmpty() && waiters.peek() != null) {
            return true;
        }
        return false;
    }
}
