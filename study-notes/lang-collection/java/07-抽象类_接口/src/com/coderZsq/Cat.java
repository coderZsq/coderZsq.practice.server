package com.coderZsq;

public class Cat implements Eatable {
    @Override
    public void eat(String name) {
        Eatable.super.eat(name);
        System.out.println("Cat - eat - " + name);
    }
}
