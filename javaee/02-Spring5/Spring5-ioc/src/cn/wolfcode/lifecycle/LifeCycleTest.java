package cn.wolfcode.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.Cleanup;

public class LifeCycleTest {

	@Test
	void test() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"cn/wolfcode/lifecycle/App-context.xml");
		MyDataSource ds = ctx.getBean("ds", MyDataSource.class);
		ds.doWork();
		ctx.close();
	}

	@Test
	void test2() throws Exception {
		@Cleanup
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"cn/wolfcode/lifecycle/App-context.xml");
		MyDataSource ds = ctx.getBean("ds", MyDataSource.class);
		ds.doWork();
	}

	@Test
	void test3() throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"cn/wolfcode/lifecycle/App-context.xml");
		MyDataSource ds = ctx.getBean("ds", MyDataSource.class);
		ds.doWork();
		ctx.registerShutdownHook();
	}

}
