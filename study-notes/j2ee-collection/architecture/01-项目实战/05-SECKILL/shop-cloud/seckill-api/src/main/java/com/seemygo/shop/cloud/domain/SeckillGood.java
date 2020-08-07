package com.seemygo.shop.cloud.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class SeckillGood implements Serializable {

    private Long id;
    private Long goodId;
    private BigDecimal seckillPrice;
    private Integer stockCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
}
