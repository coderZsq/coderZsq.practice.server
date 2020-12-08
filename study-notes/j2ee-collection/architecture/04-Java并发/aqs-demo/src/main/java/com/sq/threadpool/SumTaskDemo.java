package com.sq.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SumTaskDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        final SumTask sumTask = new SumTask(10, 20);
        new Thread(sumTask).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main..."+sumTask.getSum());
        //运行实现callable接口的实现类
        final SumTask2 sumTask2 = new SumTask2(10, 20);
        // SumTask2(Callable) ---> Runnable
        final FutureTask futureTask = new FutureTask(sumTask2);
        new Thread(futureTask).start();
        final Object result = futureTask.get();
        System.out.println("result = " + result);
    }
}
