package cn.wolfcode.di;

import javax.annotation.Resource;

public class Person {
	
	@Resource(name="cat2")
	private Cat cat1;
	
	public String toString() {
		return "Person [c1=" + cat1 + "]";
	}
}
