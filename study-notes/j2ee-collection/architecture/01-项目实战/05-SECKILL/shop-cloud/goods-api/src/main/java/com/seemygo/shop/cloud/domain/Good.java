package com.seemygo.shop.cloud.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class Good implements Serializable {

    private Long id;
    private String goodName;
    private String goodTitle;
    private String goodImg;
    private String goodDetail;
    private BigDecimal goodPrice;
    private Integer goodStock;
}
