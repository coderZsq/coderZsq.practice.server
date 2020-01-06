package cn.wolfcode.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class App {

	@Autowired
	private SomeBean bean1;
	@Autowired
	private SomeBean bean2;
	@Test
	void test() throws Exception {
		System.out.println(bean1);
		System.out.println(bean2);
		bean1.doWork();
	}
}
