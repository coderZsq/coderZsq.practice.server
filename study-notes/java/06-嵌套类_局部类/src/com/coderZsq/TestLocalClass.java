package com.coderZsq;

public class TestLocalClass {
    private int a = 1;
    private static int b = 2;

    private static void test1() {
    }

    private void test2() {
    }

    private void test3() {
        int c = 2;

        /*
         * 局部类(Local Class)
         *
         * 局部类:定义在代码块中的类(可以定义在方法中、for 循环中、if 语句中等)
         * 局部类不能定义除编译时常量以外的任何 static 成员
         * 局部类只能访问 final 或者 有效 final 的局部变量
         * 从 Java 8 开始，如果局部变量没有被第二次赋值，就认定为是有效 final
         * 局部类可以直接访问外部类中的所有成员(即使被声明为 private)
         * 局部类只有定义在实例相关的代码块中，才能直接访问外部类中的实例成员(实例变量、实例方法)
         * */
        class LocalClass {
            static final int d = 4;

            void test4() {
                System.out.println(a + b + c + d);
                test1();
                test2();
            }
        }

        new LocalClass().test4();
    }
}
