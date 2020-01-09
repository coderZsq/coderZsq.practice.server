package com.coderZsq._02_javabean;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Person {
    private Long id;
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
