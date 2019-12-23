package com.coderZsq;

public class Person implements Runnable2 {
    @Override
    public void run() {
        System.out.println("Person - run");
    }

    public int age = 1;
    public int getPAge() {
        return age;
    }
}
