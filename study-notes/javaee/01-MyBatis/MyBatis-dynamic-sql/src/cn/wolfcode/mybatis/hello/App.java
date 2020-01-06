package cn.wolfcode.mybatis.hello;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.wolfcode.mybatis.hello.domain.Employee;
import cn.wolfcode.mybatis.hello.mapper.EmployeeMapper;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class App {

	//需求1:查询工资范围在1000~2000之间的员工
	@Test
	void test1() throws Exception {
		EmployeeMapper employeeMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
		BigDecimal minSalary = new BigDecimal("1000");
		BigDecimal maxSalary = new BigDecimal("2000");
		List<Employee> es = employeeMapper.query(null, null, null);
		for (Employee e : es) {
			System.out.println(e);
		}
	}

	//需求:查询指定部门的员工信息
	@Test
	void test2() throws Exception {
		EmployeeMapper employeeMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
		List<Employee> es = employeeMapper.query(null, null, -1L);
		for (Employee e : es) {
			System.out.println(e);
		}
	}

	@Test
	void testBatchDelete() throws Exception {
		EmployeeMapper employeeMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
		employeeMapper.batchDelete(Arrays.asList(10L, 20L, 30L));
	}

	@Test
	void testBatchSave() throws Exception {
		List<Employee> list = new ArrayList<>();
		
		Employee e1 = new Employee();
		e1.setName("A");
		e1.setSn("AA");
		e1.setSalary(new BigDecimal("10.00"));
		Employee e2 = new Employee();
		e2.setName("A");
		e2.setSn("AA");
		e2.setSalary(new BigDecimal("10.00"));
		
		list.add(e1);
		list.add(e2);
		
		EmployeeMapper employeeMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
		employeeMapper.batchSave(list);
		
	}
}
