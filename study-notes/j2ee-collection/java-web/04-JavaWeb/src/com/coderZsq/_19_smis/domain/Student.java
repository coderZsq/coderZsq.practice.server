package com.coderZsq._19_smis.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Student {
    private Long id;
    private String name;
    private Integer age;

    public Student() {}

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
