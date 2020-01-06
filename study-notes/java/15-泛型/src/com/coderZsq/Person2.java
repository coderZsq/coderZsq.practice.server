package com.coderZsq;

public class Person2<T extends Number> {
    private T age;

    public <E> Person2(E name, T age) {

    }

    public Person2(T age) {
        this.age = age;
    }

    public int getAge() {
        return (age == null) ? 0 : age.intValue();
    }
}
