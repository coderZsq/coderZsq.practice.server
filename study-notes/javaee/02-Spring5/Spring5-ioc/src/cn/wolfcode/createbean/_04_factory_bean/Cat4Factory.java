package cn.wolfcode.createbean._04_factory_bean;

import org.springframework.beans.factory.FactoryBean;

public class Cat4Factory implements FactoryBean<Cat4> {

	private String username;

	public void setUsername(String username) {
		this.username = username;
	}

	//实例工厂方法:
	public Cat4 getObject() throws Exception {
		System.out.println("username=" + username);
		Cat4 c4 = new Cat4();
		//TODO
		return c4;
	}

	public Class<?> getObjectType() {
		return Cat4.class;
	}
}
