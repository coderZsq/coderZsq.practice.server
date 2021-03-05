package com.sq.dp.designpattern.singleton;

/**
 * 单例模式 (懒汉式-双重检查)
 * 优点:
 *  无论是多线程还是单线程都能够保证只能获取到一个实例, 并且由于双重保障机制能够很大程度上提升访问效率问题
 */
public class Singleton6 {
    private static volatile Singleton6 instance;

    private Singleton6() {}

    public static Singleton6 getInstance() {
        // 获取前先判断该变量是否已经初始化
        if (instance == null) {
            synchronized (Singleton6.class) {
                if (instance == null) {
                    // 没有初始化才进行初始化
                    // 申请空间()
                    // 实例化对象
                    // 变量赋值的指令
                    instance = new Singleton6();
                }
            }
        }

        // 如果已经初始化就返回该变量
        return instance;
    }
}
