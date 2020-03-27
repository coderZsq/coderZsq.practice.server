package com.coderZsq.crm.query;


import lombok.Data;

@Data
public class CustomerQueryObject extends QueryObject {
    //状态值
    private Integer status=-1;
    // 销售人员
    private Long sellerId=-1L;
}