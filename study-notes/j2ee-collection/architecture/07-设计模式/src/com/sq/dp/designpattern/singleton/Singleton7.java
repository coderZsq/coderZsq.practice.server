package com.sq.dp.designpattern.singleton;

/**
 * 单例模式 (静态内部类的方式)
 * 优点:
 *  利用JVM静态类加载机制, 内部静态类只有在被用到的时候才会被加载, 因此实现了懒加载的效果
 *  并且由于静态变量只会初始化一次, 因此也不存在线程安全问题
 */
public class Singleton7 {
    private Singleton7() {}

    private static class SingletonHolder {
        private final static Singleton7 INSTANCE = new Singleton7();
    }

    public static Singleton7 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
