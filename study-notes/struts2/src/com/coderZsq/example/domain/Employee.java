package com.coderZsq.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class Employee {
    private Long id;
    private String name;
    private String password;
    private BigDecimal salary;
}
