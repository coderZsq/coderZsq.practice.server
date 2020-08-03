package com.coderzsq._04_mybatis.domain;

import java.io.Serializable;
import lombok.Data;


/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2020-03-20 14:10:40
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 480719669759263541L;
    
    private Long id;
    
    private String name;
    
    private String expression;
}