package com.sq.dp.designpattern.singleton;

/**
 * 单例模式 (懒汉式)
 * 优点:
 *  在单线程环境下实现了懒加载, 并且无锁效率较高
 * 缺点:
 *  多线程环境下线程不安全, 可能两个线程同时进入到if语句, 此时会产生多个实例对象
 */
public class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {}

    public static Singleton3 getInstance() {
        // 获取前先判断该变量是否已经初始化
        if (instance == null) {
            // 没有初始化才进行初始化
            instance = new Singleton3();
        }

        // 如果已经初始化就返回该变量
        return instance;
    }
}
