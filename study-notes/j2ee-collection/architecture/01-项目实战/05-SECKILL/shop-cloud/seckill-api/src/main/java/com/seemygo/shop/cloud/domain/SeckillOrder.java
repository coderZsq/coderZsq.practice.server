package com.seemygo.shop.cloud.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SeckillOrder implements Serializable {
    private Long id;
    private String orderNo;
    private Long userId;
    private Long seckillId;
}
