package com.coderZsq;


public class Person {
    public void run() {}
    public void eat() {}
    public Person() {}

    private int age;
    public Person(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != Person.class) return false;
        return ((Person) obj).age == age;
    }
}
