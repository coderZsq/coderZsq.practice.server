package cn.wolfcode.mybatis.hello.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Employee {
	private Long id;
	private String name;
	private String sn;
	private BigDecimal salary;
	private Long deptId;
}
