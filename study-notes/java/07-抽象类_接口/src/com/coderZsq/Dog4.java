package com.coderZsq;

public class Dog4 implements Runnable, Walkable {
    @Override
    public void run() {
        Runnable.super.run();
        Walkable.super.run();
        System.out.println("Dog - run");
    }

    public static void main(String[] args) {
        /*
         * 默认方法的细节
         *
         * 如果(父)接口定义的默认方法与其他(父)接口定义的方法相同时，要求子类型实现此默认方法
         * */
        Dog4 dog = new Dog4();
        dog.run();
        // Runnable - run
        // Walkable - run
        // Dog - run
    }
}
