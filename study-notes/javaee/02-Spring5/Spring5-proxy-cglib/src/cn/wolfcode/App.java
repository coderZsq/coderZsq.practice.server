package cn.wolfcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.service.impl.EmployeeServiceImpl;
import cn.wolfcode.tx.TransactionManagerAdvice;

@SpringJUnitConfig
public class App {

	@Autowired
	private TransactionManagerAdvice advice;

	//JDK代理对象:com.sun.proxy.$Proxy19
	//CGLIB代理对象:cn.wolfcode.service.impl.EmployeeServiceImpl$$EnhancerByCGLIB$$c0c52615
	@Test
	void testSave() throws Exception {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"C:/test");
		EmployeeServiceImpl proxy = advice.getProxyObject();
		proxy.save(new Employee());
	}

	@Test
	void testUpdate() throws Exception {
		EmployeeServiceImpl proxy = advice.getProxyObject();
		proxy.update(new Employee());
	}
}
