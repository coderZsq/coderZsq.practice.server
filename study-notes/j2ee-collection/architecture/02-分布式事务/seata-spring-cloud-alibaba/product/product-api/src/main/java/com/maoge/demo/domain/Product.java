package com.maoge.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private BigDecimal price;
    private Integer stock;
    private LocalDateTime lastUpdateTime;
}
