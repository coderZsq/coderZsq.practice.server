package cn.wolfcode.mybatis.hello.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
	private Long id;
	private String name;
	private List<Teacher> teachers = new ArrayList<>();
	
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
}
