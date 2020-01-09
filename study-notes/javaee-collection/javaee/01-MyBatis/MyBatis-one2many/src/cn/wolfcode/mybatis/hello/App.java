package cn.wolfcode.mybatis.hello;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import cn.wolfcode.mybatis.hello.domain.Department;
import cn.wolfcode.mybatis.hello.mapper.DepartmentMapper;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class App {

	
	//查询10号部门的信息和包含的员工信息
	@Test
	void testGet() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
		Department d = departmentMapper.get(10L);
		System.out.println(d.getClass());
		System.out.println(d.getEmps());
	}
}
