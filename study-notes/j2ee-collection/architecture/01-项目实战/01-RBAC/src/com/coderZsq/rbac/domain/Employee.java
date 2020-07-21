package com.coderZsq.rbac.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Employee {
    /**
     * id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 年龄
     */
    private int age;
    /**
     * 是否管理员
     */
    private boolean admin;
    /**
     * 所属部门
     */
    private Department department;
}
