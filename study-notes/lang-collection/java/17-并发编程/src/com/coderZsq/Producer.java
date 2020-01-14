package com.coderZsq;

/*
 * 线程间通信 - 示例 - Producer
 * */
public class Producer implements Runnable {
    private Drop drop;
    public Producer(Drop drop) {
        this.drop = drop;
    }
    @Override
    public void run() {
        String foods[] = {"beef", "bread", "apple", "cookie"};
        for (int i = 0; i < foods.length; i++) {
            drop.add(foods[i]);
        }
        drop.add(null);
    }
}
