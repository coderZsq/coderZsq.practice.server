package com.sq.mongo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Document("users")
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private List<String> hobby = new ArrayList<>();
}