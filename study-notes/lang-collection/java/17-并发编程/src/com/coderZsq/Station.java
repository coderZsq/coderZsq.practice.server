package com.coderZsq;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Station implements Runnable {
    private int tickets = 100;

    /**
     * 卖一张票
     *
     * @return 是否还有票可以卖
     */
    public boolean saleTicket0() {
        if (tickets < 1) return false;
        tickets--;
        String name = Thread.currentThread().getName();
        System.out.println(name + "卖了1张, 剩" + tickets + "张");
        return tickets > 0;
    }

    /*
     * 线程同步
     *
     * 可以使用线程同步技术来解决线程安全问题
     * 同步语句(Synchronized Statement)
     * 同步方法(Synchronized Method)
     * */

    /*
     * 线程同步 - 同步语句
     *
     * synchronized obj) 的原理
     * 每个对象都有一个与它相关的内部锁(intrinsic lock)或者叫监视器锁(monitor lock)
     * 第一个执行到同步语句的线程可以获得 obj 的内部锁，在执行完同步语句中的代码后释放此锁
     * 只要一个线程持有了内部锁，那么其它线程在同一时刻将无法再获得此锁
     * 当它们试图获取此锁时，将会进入 BLOCKED 状态
     *
     * 多个线程访问同一个synchronized obj) 语句时
     * obj 必须是同一个对象，才能起到同步的作用
     * */
    public boolean saleTicket1() {
        synchronized (this) {
            if (tickets < 1) return false;
            tickets--;
            String name = Thread.currentThread().getName();
            System.out.println(name + "卖了1张, 剩" + tickets + "张");
            return tickets > 0;
        }
    }

    /*
     * 线程同步 – 同步方法
     *
     * synchronized 不能修饰构造方法
     *
     * 同步方法的本质
     * 实例方法:synchronized (this)
     * 静态方法:synchronized (Class对象)
     *
     * 同步语句比同步方法更灵活一点
     * 同步语句可以精确控制需要加锁的代码范围
     *
     * 使用了线程同步技术后
     * 虽然解决了线程安全问题，但是降低了程序的执行效率
     * 所以在真正有必要的时候，才使用线程同步技术
     * */
    public synchronized boolean saleTicket2() {
        if (tickets < 1) return false;
        tickets--;
        String name = Thread.currentThread().getName();
        System.out.println(name + "卖了1张, 剩" + tickets + "张");
        return tickets > 0;
    }

    /*
     * ReentrantLock 在卖票示例中的使用
     * */
    private Lock lock = new ReentrantLock();
    public boolean saleTicket() {
        try {
            lock.lock();
            if (tickets < 1) return false;
            tickets--;
            String name = Thread.currentThread().getName();
            System.out.println(name + "卖了1张, 剩" + tickets + "张");
            return tickets > 0;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (saleTicket()) ;
    }
}
