package com.sq.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@ConfigurationProperties("cat")
@ConstructorBinding
@Data
public class Cat {
    private Integer id;
    private String name;

    public Cat(Integer id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("Cat-------------");
    }
}
