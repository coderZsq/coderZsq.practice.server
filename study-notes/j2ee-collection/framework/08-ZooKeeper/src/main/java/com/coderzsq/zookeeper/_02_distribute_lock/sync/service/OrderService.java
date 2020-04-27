package com.coderzsq.zookeeper._02_distribute_lock.sync.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * 模拟多线程, 调用获取订单id的方法
 */
public class OrderService implements Runnable {

    //用来保存所有的订单id   1 去重操作
    private static Set<String> ids = new HashSet<>();
    // CountDownLatch 并发编程的api 发令抢 主要用于程序等待
    private static CountDownLatch countDownLatch = new CountDownLatch(50);
    //id生成器
    private OrderIdGenerator orderIdGenerator = new OrderIdGenerator();

    public static void main(String[] args) throws Exception {
        OrderService orderService = new OrderService();//i=0
        //开启50个线程
        for (int i = 0; i < 50; i++) {
            new Thread(orderService).start();
        }
        //等待所有线程执行完成, 在去执行主线程
        countDownLatch.await();//等待状态, countDownLatch 中计数器的值变为0
        System.out.println("获取到的订单的id数量:" + ids.size());
        System.out.println("==============================");
        System.out.println(ids);
    }

    @Override
    public void run() {
        //调用id生成的方法  多线程执行的任务
        String id = orderIdGenerator.getId();
        ids.add(id);
        countDownLatch.countDown();//让计数器的值减一
    }
}
