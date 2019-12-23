package com.coderZsq;

public class Dog2 extends Animal implements Runnable {
    public static void main(String[] args) {
        /*
         * 默认方法的细节
         *
         * 如果父类定义的非抽象方法与接口的默认方法相同时，最终将调用父类的方法
         * */
        Dog2 dog = new Dog2();
        dog.run();
        // Animal - run
    }
}
