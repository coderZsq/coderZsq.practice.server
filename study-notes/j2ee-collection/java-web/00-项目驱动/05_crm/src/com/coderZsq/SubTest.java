package com.coderZsq;

import java.lang.reflect.Method;

public class SubTest extends Test {
    public void subTest1() {

    }

    protected void subTest2() {

    }

    private void subTest3() {

    }

    public static void main(String[] args) {
        // 获取当前类以及父类中所有的public方法
        Method[] methods = SubTest.class.getMethods();

        // 获取当前类中声明的所有方法 （即使是private)
        // Method[] methods = SubTest.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
}
