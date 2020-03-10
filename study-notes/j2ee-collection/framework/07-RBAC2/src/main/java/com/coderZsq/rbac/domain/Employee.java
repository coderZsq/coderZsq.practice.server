package com.coderZsq.rbac.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class Employee {
    private Long id;

    private String name;

    private String password;

    private String email;

    private Integer age;

    private boolean admin;//boolean类型的getter方法名为 isXxx

    private Department dept;
    //一对多
    private List<Role> roles;

}