package cn.wolfcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.tx.TransactionManagerAdvice;

@SpringJUnitConfig
public class App {

	@Autowired
	private TransactionManagerAdvice advice;

	//代理对象:com.sun.proxy.$Proxy19
	@Test
	void testSave() throws Exception {
		//获取代理对象
		IEmployeeService proxy = advice.getProxyObject();
		proxy.save(new Employee());
	}

	@Test
	void testUpdate() throws Exception {
		//获取代理对象
		IEmployeeService proxy = advice.getProxyObject();
		proxy.update(new Employee());
	}

	@Test
	void testDelete() throws Exception {
		//获取代理对象
		IEmployeeService proxy = advice.getProxyObject();
		proxy.delete(123L);
	}

	@Test
	void testListAll() throws Exception {
		//获取代理对象
		IEmployeeService proxy = advice.getProxyObject();
		proxy.listAll();
	}
}
