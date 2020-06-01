package com.coderZsq.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = 6452122926732908748L;
    private Long id; // 商品id
    private String name; // 商品名称
    private int price; // 商品价格
    private int stock; // 商品库存
}
