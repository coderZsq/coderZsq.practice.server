package com.coderZsq._14_hibernate.domain;

import com.coderZsq._14_hibernate.util.Id;
import com.coderZsq._14_hibernate.util.Table;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter@Table("t_student")
public class Student {
    @Id
    private Long id;
    private String name;
    private Integer age;

    public Student() {}

    public Student(String name, int age) {
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
