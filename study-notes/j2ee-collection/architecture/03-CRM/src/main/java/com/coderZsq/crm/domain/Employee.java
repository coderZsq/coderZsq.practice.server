package com.coderZsq.crm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;


/**
 * (Employee)实体类
 *
 * @author makejava
 * @since 2020-03-18 14:32:12
 */
@Data
public class Employee implements Serializable {
    private static final long serialVersionUID = 208241734780695068L;
    
    private Long id;
    
    private String name;
    
    private String password;
    
    private String email;
    
    private Integer age;
    
    private boolean admin = false;
    
    private Department dept; // 关联部门信息

    private List<Role> roles = new ArrayList<>();
}