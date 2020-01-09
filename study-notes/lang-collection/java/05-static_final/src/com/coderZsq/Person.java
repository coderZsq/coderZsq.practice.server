package com.coderZsq;

/*
 * 初始化块、静态初始化块
 *
 * 可以有多个(静态)初始化块，按照在源码中出现的顺序被执行
 * */
public class Person {
    static { // 静态初始化块
        System.out.println("Person - static block");
    }

    { // 初始化块
        System.out.println("Person - block");
    }

    public Person() {
        System.out.println("Person - constructor");
    }

    public Person(int age) {
    }

    public static void main(String[] args) {
        new Person();
        new Person(20);
    }
}
