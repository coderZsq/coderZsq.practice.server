package cn.wolfcode.lifecycle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
public class App {

	@Test
	void testOld() throws Exception {
		//创建对象
		MyDataSource ds = new MyDataSource();
		//立马对对象做初始化操作
		ds.open();

		ds.doWork();

		//在销毁之前执行扫尾操作
		ds.close();
	}

	@Autowired
	private MyDataSource dataSource;

	@Test
	void testIoC() throws Exception {
		dataSource.doWork();
	}
}