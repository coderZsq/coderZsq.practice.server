package com.sq.dp.designpattern.singleton;

/**
 * 单例模式 (懒汉式-同步代码块)
 * 缺点:
 *  压根没有实现同步的效果, 在多线程环境下是不能保障线程安全的
 */
public class Singleton5 {
    private static Singleton5 instance;

    private Singleton5() {}

    public static Singleton5 getInstance() {
        // 获取前先判断该变量是否已经初始化
        if (instance == null) {
            synchronized (Singleton5.class) {
                // 没有初始化才进行初始化
                instance = new Singleton5();
            }
        }

        // 如果已经初始化就返回该变量
        return instance;
    }
}
