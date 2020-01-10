package com.coderZsq;

/*
 * 单例模式(懒汉式)改进
 * */
public class Rocket {
    private static Rocket instance = null;
    private Rocket() {}
    public static synchronized Rocket getInstance() {
        if (instance == null) {
            instance = new Rocket();
        }
        return instance;
    }
}
