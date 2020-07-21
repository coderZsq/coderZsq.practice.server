package com.coderZsq.crm.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


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

    @Excel(name = "姓名", needMerge = true)
    private String name;

    private String password;

    @Excel(name = "邮箱", width = 20, needMerge = true)
    private String email;

    @Excel(name = "年龄", needMerge = true)
    private Integer age;

    private boolean admin = false;

    @ExcelEntity(id = "dept")
    private Department dept; // 关联部门信息

    @ExcelCollection(name = "角色")
    private List<Role> roles = new ArrayList<>(); // 关联多个角色信息
}