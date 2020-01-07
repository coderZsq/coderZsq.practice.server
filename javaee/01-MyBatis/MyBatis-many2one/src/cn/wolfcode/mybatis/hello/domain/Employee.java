package cn.wolfcode.mybatis.hello.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
	private Long id;
	private String name;
	private Department dept;//员工所在部门对象
	
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
}
