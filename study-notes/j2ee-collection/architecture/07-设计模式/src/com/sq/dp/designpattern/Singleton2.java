package com.sq.dp.designpattern;

/**
 * 单例模式 (饿汉式-静态代码块)
 * 优点:
 *  实现起来非常简单, 并且由于是静态变量, JVM只会在类加载时初始化该变量, 不存在线程安全问题
 * 缺点:
 *  由于是静态变量, 在类加载时就已经初始化了对象, 如果长时间不使用该对象, 意味着在一定程度上造成了内存浪费
 */
public class Singleton2 {
    private final static Singleton2 INSTANCE;

    static {
        INSTANCE = new Singleton2();
    }

    private Singleton2() {}

    public static Singleton2 getInstance() {
        return INSTANCE;
    }
}
