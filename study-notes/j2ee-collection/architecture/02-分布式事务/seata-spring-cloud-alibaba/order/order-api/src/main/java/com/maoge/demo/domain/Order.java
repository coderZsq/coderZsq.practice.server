package com.maoge.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer productId;
    private Integer amount;
    private Integer sum;
    private Integer accountId;
    private Date createTime;
}
