package com.coderZsq;

import java.io.IOException;

public class Person {
    public void test1() {
    }

    public void test2() throws IOException {
    }

    public void test3() throws IOException {
    }

    public void test4() throws IOException {
    }

    /*
     * throw
     *
     * 使用 throw 可以抛出一个新建的异常
     * */
    public Person(String name) throws Exception {
        if (name == null || name.length() == 0) {
            throw new Exception("name must not be empty");
        }
    }

    public Person(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("name must not be empty");
        }
    }

    /*
     * 自定义异常 – 示例
     * */
    private String name;
    private int age;
    public Person(String name, int age) {
        if (name == null || name.length() == 0) {
            throw new EmptyNameException();
        }
        if (age <= 0) {
            throw new WrongAgeException(age);
        }
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        // WrongAgeException: wrong age: -10, age must be > 0
        Person person = new Person("Jack", -10);
        // 这句代码不会执行
        System.out.println(1);
    }
}
