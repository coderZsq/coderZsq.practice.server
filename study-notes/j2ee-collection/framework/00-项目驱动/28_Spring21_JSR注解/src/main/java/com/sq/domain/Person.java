package com.sq.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.inject.Named;

@Component
public class Person {
    private Dog dog;
    private String name;

    public Person() {
        System.out.println("Person--------------------");
    }

    @Autowired
    public Person(@Named("dog2") Dog dog, @Nullable String name) {
        this.dog = dog;
        this.name = name;
        // System.out.println("Person(Dog, String)--------------------");
    }

    @Override
    public String toString() {
        return "Person{" +
                "dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}
