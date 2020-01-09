package cn.wolfcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.service.IEmployeeService;

@SpringJUnitConfig
public class App {

	@Autowired
	private IEmployeeService service;

	@Test
	void testSave() throws Exception {
		System.out.println(service.getClass());
		service.save(new Employee());
	}

	@Test
	void testUpdate() throws Exception {
		service.update(new Employee());
	}
}
