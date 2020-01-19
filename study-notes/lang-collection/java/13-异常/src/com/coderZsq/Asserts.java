package com.coderZsq;

/*
 * 练习 – 编写一个断言类
 * */
public class Asserts {
    public static void test(boolean v) {
        if (v) return;
        try {
            throw new IllegalArgumentException("条件不成立");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
