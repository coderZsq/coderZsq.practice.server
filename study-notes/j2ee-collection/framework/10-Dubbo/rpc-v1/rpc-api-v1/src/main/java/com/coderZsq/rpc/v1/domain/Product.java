package com.coderZsq.rpc.v1.domain;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 产品信息
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = -6820562343708349961L;
    private Long id; // id;
    private String sn; // 产品编号
    private String name; // 产品名称
    private BigDecimal price; // 产品价格
}
