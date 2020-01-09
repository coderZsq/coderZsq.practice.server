package cn.wolfcode.di;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class App {

	@Resource
	private Person person;
	
	@Autowired
	
	private ValueBean bean;
	@Test
	void test() throws Exception {
		System.out.println(person);
		System.out.println(bean);
	}
}
