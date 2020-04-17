package com.coderZsq.crm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;


/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-03-18 15:50:24
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 395437432377638347L;
    
    private Long id;

    @Excel(name = "名称")
    private String name;

    @Excel(name = "编码")
    private String sn;

    // 权限关联关系的维护
    private List<Permission> permissions = new ArrayList<>();
}