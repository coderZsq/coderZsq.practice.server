package com.maoge.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String userName;
    private Integer sum; //账户余额
    private LocalDateTime lastUpdateTime;
}
