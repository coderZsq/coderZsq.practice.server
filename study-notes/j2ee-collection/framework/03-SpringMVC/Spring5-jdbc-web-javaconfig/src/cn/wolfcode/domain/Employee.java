package cn.wolfcode.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Employee {
	private Long id;
	private String username;
	private String password;
	private Integer age;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date hiredate;
}
