package com.coderZsq._05_smis.domain;

import lombok.Getter;
import lombok.Setter;

// 学生信息对象
@Setter@Getter
public class Student {
    private Long id;
    private String name; // 姓名
    private Integer age; // 年龄

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
