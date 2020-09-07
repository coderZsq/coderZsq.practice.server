package com.sq.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
    @Autowired
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "dog=" + dog +
                '}';
    }
}
