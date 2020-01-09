package com.coderZsq;

public interface Testable extends Runnable, Walkable {
    /*
     * 默认方法的细节
     *
     * 如果(父)接口定义的默认方法与其他(父)接口定义的方法相同时，要求子类型实现此默认方法
     * */
    @Override
    default void run() {
        Runnable.super.run();
        Walkable.super.run();
        System.out.println("Testable - run");
    }
}
