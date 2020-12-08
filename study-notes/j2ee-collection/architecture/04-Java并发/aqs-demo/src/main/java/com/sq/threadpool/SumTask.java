package com.sq.threadpool;

public class SumTask implements Runnable {
    private int a, b;
    private volatile int sum;

    public SumTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public void run() {
        System.out.println("SumTask.run");
        sum = a + b;
        System.out.println("sum = " + sum);
    }
}
