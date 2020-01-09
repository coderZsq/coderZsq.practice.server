package cn.wolfcode.mybatis.hello.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class User {
	private Long id;
	private String name;
	private BigDecimal salary;
}
