package com.sq.domain;

import lombok.Data;

import java.util.List;

@Data
public class Person {
    private Integer id;
    private Integer age;
    private String name;
    private String[] nickNames;
    private Dog dog;
    private List<Dog> dogs;

    @Data
    public static class Dog {
        private Integer age;
        private String name;
    }
}
