package com.sq.threadpool;

import java.util.concurrent.*;

public class ExecutorsDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建一个无界队列的线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建指定线程的线程池
        final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        // 创建单个线程池
        final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        // execute 启动的线程任务是没有返回值 runnable资源
        executorService.execute(()->{
            System.out.println("execute... run.. 线程池");
        });
        // 有返回值得结果
        Future<String> future = executorService.submit(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println(123);
                        return  "result..";
                    }
                }
        );
        System.out.println("future.get() = " + future.get());

        // 需要销毁线程
        executorService.shutdown();
        fixedThreadPool.shutdown();
        singleThreadExecutor.shutdown();
    }
}
