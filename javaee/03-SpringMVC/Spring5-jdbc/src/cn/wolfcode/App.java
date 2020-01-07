package cn.wolfcode;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.service.IEmployeeService;

@SpringJUnitConfig(locations="classpath:applicationContext.xml")
public class App {

	@Autowired
	private IEmployeeService service;

	@Test
	void testSave() throws Exception {
		Employee e = new Employee();
		e.setUsername("乔峰");
		e.setPassword("1111");
		e.setAge(30);
		service.save(e);
	}

	@Test
	void testUpdate() throws Exception {
		Employee e = new Employee();
		e.setUsername("西门吹雪");
		e.setPassword("0000");
		e.setAge(28);
		e.setId(2L);
		service.update(e);
	}

	@Test
	void testDelete() throws Exception {
		service.delete(9L);
	}

	@Test
	void testGet() throws Exception {
		Employee e = service.get(8L);
		System.out.println(e);
	}

	@Test
	void testListAll() throws Exception {
		List<Employee> list = service.listAll();
		for (Employee e : list) {
			System.out.println(e);
		}
	}
}
