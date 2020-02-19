package com.coderZsq.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商品对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("product")
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
