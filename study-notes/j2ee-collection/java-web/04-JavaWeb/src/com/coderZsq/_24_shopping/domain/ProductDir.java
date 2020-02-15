package com.coderZsq._24_shopping.domain;

import lombok.Data;

/**
 * 商品分类对象
 */
@Data
public class ProductDir {
    private Long id;
    private String dirName;
    private Long parent_id;
}
