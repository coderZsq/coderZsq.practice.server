package com.coderZsq._25_shoppingcart.domain;

import lombok.Data;

import java.math.BigDecimal;

// 购物车中的商品对象
@Data
public class CartItem {
    private String id; // 唯一的ID
    private String name; // 商品名称
    private BigDecimal price; // 商品单价
    private Integer num; // 购买数量
}
