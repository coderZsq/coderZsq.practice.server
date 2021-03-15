package com.sq.dp.designpattern.singleton;

/**
 * 单例模式 (饿汉式-静态常量)
 * 优点:
 *  实现起来非常简单, 并且由于是静态变量, JVM只会在类加载时初始化该变量, 不存在线程安全问题
 * 缺点:
 *  由于是静态变量, 在类加载时就已经初始化了对象, 如果长时间不使用该对象, 意味着在一定程度上造成了内存浪费
 */
public class Singleton1 {
    private final static Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {}

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
