package cn.wolfcode.mybatis.hello;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.wolfcode.mybatis.hello.domain.Employee;
import cn.wolfcode.mybatis.hello.mapper.EmployeeMapper;
import cn.wolfcode.mybatis.hello.query.EmployeeQueryObject;
import cn.wolfcode.mybatis.hello.service.IEmployeeService;
import cn.wolfcode.mybatis.hello.service.impl.EmployeeServiceImpl;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class App {

	@Test
	void testPageHelper() throws Exception {
		EmployeeMapper employeeMapper = MyBatisUtil.getMapper(EmployeeMapper.class);
		//------------------------------------------------
		//设置分页:
		PageHelper.startPage(1, 2);
		//------------------------------------------------
		List<Employee> list = employeeMapper.listAll();
		for (Employee e : list) {
			System.out.println(e);
		}

		PageInfo pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getTotal());
		System.out.println(pageInfo.getList());
		System.out.println(pageInfo.getPageNum() + "/" + pageInfo.getPages());
		System.out.println(pageInfo.getPrePage());
		System.out.println(pageInfo.getNextPage());

	}

	@Test
	void testQuery() throws Exception {
		EmployeeQueryObject qo = new EmployeeQueryObject();
		qo.setCurrentPage(1);
		qo.setPageSize(3);
		IEmployeeService service = new EmployeeServiceImpl();
		//qo.setKeyword("2");
		qo.setMinSalary(new BigDecimal("1000"));
		//qo.setMaxSalary(new BigDecimal("2000"));
		//qo.setDeptId(30L);
		PageInfo pageInfo = service.query(qo);
		System.out.println(pageInfo.getTotal());
		for (Object o : pageInfo.getList()) {
			System.out.println(o);
		}
		
	}

}
