package cn.wolfcode.di;

import org.springframework.beans.factory.annotation.Autowired;

public class SomeBean {
	private OtherBean other1;
	private OtherBean other2;
	
	@Autowired
	public void setOther1(OtherBean other1) {
		this.other1 = other1;
	}
	
	@Autowired
	public void setOther2(OtherBean other2) {
		this.other2 = other2;
	}
	
	public String toString() {
		return "SomeBean [other1=" + other1 + ", other2=" + other2 + "]";
	}
}
