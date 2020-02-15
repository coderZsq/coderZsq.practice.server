package com.coderZsq._24_shopping.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品对象
 */
@Data
public class Product {
    private Long id;
    private String productName;
    private String brand;
    private String supplier;
    private BigDecimal salePrice;
    private BigDecimal costPrice;
    private Double cutoff;
    private Long dir_id;
}
