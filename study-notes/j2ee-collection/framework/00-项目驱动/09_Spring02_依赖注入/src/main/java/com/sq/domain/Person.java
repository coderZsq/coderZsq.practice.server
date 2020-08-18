package com.sq.domain;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Set;

public class Person {
    private int age;
    private String name;
    private BigDecimal money;
    private Dog dog;
    private Set<String> nickNames;
    private Properties friends;

    public Person() {
        System.out.println("Person()");
    }

    public Person(int age) {
        this.age = age;
        System.out.println("Person(" + age + ")");
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
        System.out.println("Person(" + age + ", " + name + ")");
    }

    public Properties getFriends() {
        return friends;
    }

    public void setFriends(Properties friends) {
        this.friends = friends;
    }

    public Set<String> getNickNames() {
        return nickNames;
    }

    public void setNickNames(Set<String> nickNames) {
        this.nickNames = nickNames;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", dog=" + dog +
                '}';
    }
}
