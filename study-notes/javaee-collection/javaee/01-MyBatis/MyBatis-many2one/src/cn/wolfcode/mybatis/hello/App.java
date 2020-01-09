package cn.wolfcode.mybatis.hello;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import cn.wolfcode.mybatis.hello.domain.Department;
import cn.wolfcode.mybatis.hello.domain.Employee;
import cn.wolfcode.mybatis.hello.mapper.DepartmentMapper;
import cn.wolfcode.mybatis.hello.mapper.EmployeeMapper;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class App {

	//保存一个部门和两个员工
	@Test
	void testSave() throws Exception {
		Department d = new Department();
		d.setName("开发部");

		Employee e1 = new Employee();
		e1.setName("张三");

		Employee e2 = new Employee();
		e2.setName("李四");

		//维护对象关系
		e1.setDept(d);
		e2.setDept(d);

		SqlSession session = MyBatisUtil.getSession();
		DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		//保存操作

		departmentMapper.save(d);
		employeeMapper.save(e1);
		employeeMapper.save(e2);
		session.commit();
	}

	@Test
	void testGet() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
		Employee e = employeeMapper.get(1L);
		System.out.println(e.toString());
		System.out.println(e.getDept());
		
		System.out.println(e.getClass());//动态代理类
	}

	@Test
	void testListAll() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

		List<Employee> es = employeeMapper.listAll();
		for (Employee e : es) {
			System.out.println(e);
		}
	}
}
