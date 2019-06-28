package com.coderZsq.tag;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private Long id;
    private String name;
    private Integer age;

    public Student() {
    }

    public Student(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public static List<Student> listAll() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1L, "1", 1));
        list.add(new Student(2L, "2", 2));
        list.add(new Student(3L, "3", 3));
        list.add(new Student(4L, "4", 4));
        return list;
    }
}
