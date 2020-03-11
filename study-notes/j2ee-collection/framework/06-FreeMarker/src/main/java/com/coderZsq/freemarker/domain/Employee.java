package com.coderZsq.freemarker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private String password;
    private String email;
    private Integer age;
    private boolean admin;//boolean类型的getter方法名为 isXxx
    private Department dept;
}