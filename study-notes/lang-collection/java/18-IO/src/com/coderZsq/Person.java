package com.coderZsq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private int age;
    private String name;
    private Car car;
    private List<Book> books = new ArrayList<>();

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", car=" + car +
                ", books=" + books +
                '}';
    }
}
