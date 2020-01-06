package cn.wolfcode.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import cn.wolfcode.register.action.UserAction;

@SpringJUnitConfig
public class App {

	@Autowired
	private SomeBean someBean;

	@Test
	void test() throws Exception {
		System.out.println(someBean);
	}
}
