package com.sq.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("user.properties")
public class User {
    @Value("${name}")
    private String name;
    private Dog dog;

    // public User(Dog dog) {
    //     System.out.println("----------------");
    //     this.dog = dog;
    // }

    public void setName(String name) {
        this.name = name;
    }

    @Autowired(required = false)
    @Qualifier("dog")
    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", dog=" + dog +
                '}';
    }
}
