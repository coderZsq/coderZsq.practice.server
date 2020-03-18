package com.coderZsq.crm.domain;

import java.io.Serializable;
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
    
    private String name;
    
    private String sn;
}