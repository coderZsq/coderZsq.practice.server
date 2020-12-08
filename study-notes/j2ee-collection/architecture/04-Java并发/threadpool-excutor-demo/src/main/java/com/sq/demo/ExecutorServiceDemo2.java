package com.sq.demo;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo2 {
    public static void main(String[] args) {
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        final Date createDate = new Date();

        // executorService.schedule(new Runnable() {
        //     @Override
        //     public void run() {
        //         System.out.println("任务创建时间:" + createDate + "任务执行时间" + new Date());
        //     }
        // }, 5, TimeUnit.SECONDS);

        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务创建时间:" + createDate + "任务执行时间" + new Date());
            }
        }, 5, 2, TimeUnit.SECONDS);
        // // rocketMQ 压力性能测试, --> 多线程
    }
}
