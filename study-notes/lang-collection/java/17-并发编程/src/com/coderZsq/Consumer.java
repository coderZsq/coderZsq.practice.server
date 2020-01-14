package com.coderZsq;

/*
 * 线程间通信 - 示例 - Consumer
 * */
public class Consumer implements Runnable {
    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        String food = null;
        while ((food = drop.get()) != null) {
            System.out.println("接收到食物: " + food);
        }
    }
}
