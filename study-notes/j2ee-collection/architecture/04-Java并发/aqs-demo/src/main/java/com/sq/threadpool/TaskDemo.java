package com.sq.threadpool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TaskDemo {

    public static void main(String[] args) {
        // 创建一个可重入锁
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        //创建2个任务
        final Task1 task1 = new Task1(lock,condition);
        final Task2 task2 = new Task2(lock,condition);
        // 创建并且启动2个线程
        new Thread(task1).start();
        new Thread(task2).start();
        // 需求: 在task1 运行到标号为①的地方, task1 进入线程等待状态, task2 运行起来
        // task2 运行到 ② 的地方, 开始唤醒 task1  继续运行
    }
}
