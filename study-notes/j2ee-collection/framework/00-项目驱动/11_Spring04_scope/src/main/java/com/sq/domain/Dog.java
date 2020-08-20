package com.sq.domain;

public class Dog {
    public String getTestName() {
        return "WangCai";
    }

    public int getTestAge() {
        return 99;
    }

    public Dog() {
        System.out.println("Dog----------");
    }

    public void setName(String name) {
        System.out.println("setName - " + name);
    }
}
