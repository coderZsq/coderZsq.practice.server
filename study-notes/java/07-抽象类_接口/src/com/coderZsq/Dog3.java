package com.coderZsq;

public class Dog3 extends Animal2 implements Runnable {
    @Override
    public void run() {
        Runnable.super.run();
        System.out.println("Dog - run");
    }

    public static void main(String[] args) {
        /*
         * 默认方法的细节
         *
         * 如果父类定义的抽象方法与接口的默认方法相同时，要求子类实现此抽象方法
         * 可以通过 super 关键字调用接口的默认方法
         * */
        Dog3 dog = new Dog3();
        dog.run();
        // Runnable - run
        // Dog - run
    }
}
