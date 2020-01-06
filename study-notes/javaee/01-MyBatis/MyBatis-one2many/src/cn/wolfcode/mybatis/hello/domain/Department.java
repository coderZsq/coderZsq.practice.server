package cn.wolfcode.mybatis.hello.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Department {
	private Long id;
	private String name;
	//一个部门关联多个员工
	private List<Employee> emps = new ArrayList<Employee>();
	
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
}
