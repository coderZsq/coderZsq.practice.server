package com.coderZsq;

/*
 * 单例模式(Singleton Pattern)
 *
 * 如果一个类设计成单例模式，那么在程序运行过程中，这个类只能创建一个实例
 * */

// 饿汉式
public class Rocket {
    private static Rocket instance = new Rocket();

    private Rocket() {
    }

    public static Rocket getInstance() {
        return instance;
    }
}

// 懒汉式 (有线程安全问题)
class Rocket2 {
    private static Rocket2 instance = null;

    private Rocket2() {
    }

    public static Rocket2 getInstance() {
        if (instance == null) instance = new Rocket2();
        return instance;
    }
}