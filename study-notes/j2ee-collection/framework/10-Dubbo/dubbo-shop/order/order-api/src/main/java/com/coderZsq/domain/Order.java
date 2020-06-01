package com.coderZsq.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

// 订单业务
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = -2864268706774796263L;
    private String orderNo; // 订单编号
    private Date createTime; // 下单时间
    private String productName; // 产品名称
    private int productPrice; // 产品价格
    private Long userId; // 用户id
}
