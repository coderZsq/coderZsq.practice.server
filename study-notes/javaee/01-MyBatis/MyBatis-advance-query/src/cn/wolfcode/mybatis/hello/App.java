package cn.wolfcode.mybatis.hello;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.wolfcode.mybatis.hello.domain.Employee;
import cn.wolfcode.mybatis.hello.mapper.EmployeeMapper;
import cn.wolfcode.mybatis.hello.query.EmployeeQueryObject;
import cn.wolfcode.mybatis.hello.query.PageResult;
import cn.wolfcode.mybatis.hello.service.IEmployeeService;
import cn.wolfcode.mybatis.hello.service.impl.EmployeeServiceImpl;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class App {

	
	@Test
	void testPageHelper() throws Exception {
		EmployeeMapper employeeMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
		
		
		
	}
	
	
	
	
	
	
	
	
	@Test
	void testQuery() throws Exception {
		EmployeeQueryObject qo = new EmployeeQueryObject();
		IEmployeeService service = new EmployeeServiceImpl();
		qo.setKeyword("2");
		//qo.setMinSalary(new BigDecimal("1000"));
		//qo.setMaxSalary(new BigDecimal("2000"));
		//qo.setDeptId(30L);
		PageResult pageResult = service.query(qo);
		System.out.println(pageResult.getTotalCount());
		for (Object o : pageResult.getResult()) {
			System.out.println(o);
		}
	}

	//需求:按照员工的关键字(abc),工资范围,所属部门来查询

	//高级查询
	@Test
	void test1() throws Exception {
		EmployeeQueryObject qo = new EmployeeQueryObject();
		//qo.setKeyword("  ");
		qo.setMinSalary(new BigDecimal("1000"));
		qo.setMaxSalary(new BigDecimal("2000"));
		qo.setDeptId(30L);
		//----------------------------------------
		EmployeeMapper employeeMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
		List<Employee> es = employeeMapper.queryForList(qo);
		for (Employee e : es) {
			System.out.println(e);
		}
	}

	@Test
	void test2() throws Exception {
		EmployeeQueryObject qo = new EmployeeQueryObject();
		qo.setMinSalary(new BigDecimal("1000"));
		qo.setMaxSalary(new BigDecimal("2000"));
		qo.setDeptId(30L);
		//----------------------------------------
		EmployeeMapper employeeMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
		int rows = employeeMapper.queryForCount(qo);
		System.out.println(rows);
	}
}
