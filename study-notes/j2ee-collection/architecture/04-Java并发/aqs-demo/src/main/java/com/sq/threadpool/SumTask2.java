package com.sq.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SumTask2 implements Callable<Integer> {
    private int a, b;
    public SumTask2(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("SumTask2.call");
        TimeUnit.SECONDS.sleep(2);
        return a+b;
    }
}
