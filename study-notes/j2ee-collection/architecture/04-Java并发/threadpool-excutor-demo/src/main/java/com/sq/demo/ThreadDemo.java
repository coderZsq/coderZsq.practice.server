package com.sq.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 方式一: 直接使用Thread创建并且启动线程
        // 线程对象和业务之间 耦合比较严重
        final Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("线程名称: " + Thread.currentThread().getName());
            }
        };
        t1.start();
        // 方式二: 通过实现Runnable接口
        // 线程Thread和任务Task 分开处理
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("线程名称: " + Thread.currentThread().getName());
            }
        };
        final Thread t2 = new Thread(task);
        // t2.start==>target!=null ==> target.run()
        t2.start();
        // 方式三: 通过Callable接口 可以返回对应的值
        final Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("线程名称: " + Thread.currentThread().getName());
                return "hello java";
            }
        };

        // FutureTask
        final FutureTask<String> futureTask = new FutureTask<>(callable);
        final Thread t3 = new Thread(futureTask);
        t3.start();
        // 获取方法返回值, get() 阻塞方法
        final String text = futureTask.get();
        System.out.println("text = " + text);
    }
}
