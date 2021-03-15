package com.sq.dp.designpattern.singleton;

/**
 * 单例模式 (懒汉式-同步方法)
 * 优点:
 *  不管是单线程还是多线程都能够实现懒加载的单例模式
 * 缺点:
 *  由于方法上加了同步锁, 方法执行效率变低
 */
public class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {}

    public static synchronized Singleton4 getInstance() {
        // 获取前先判断该变量是否已经初始化
        if (instance == null) {
            // 没有初始化才进行初始化
            instance = new Singleton4();
        }

        // 如果已经初始化就返回该变量
        return instance;
    }
}
