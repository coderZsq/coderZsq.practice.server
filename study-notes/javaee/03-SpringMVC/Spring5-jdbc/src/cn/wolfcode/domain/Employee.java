package cn.wolfcode.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Employee {
	private Long id;
	private String username;
	private String password;
	private Integer age;
	private Date hiredate;
}
