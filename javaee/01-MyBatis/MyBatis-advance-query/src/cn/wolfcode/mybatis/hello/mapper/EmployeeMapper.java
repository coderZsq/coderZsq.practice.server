package cn.wolfcode.mybatis.hello.mapper;

import java.util.List;

import cn.wolfcode.mybatis.hello.domain.Employee;
import cn.wolfcode.mybatis.hello.query.QueryObject;

public interface EmployeeMapper {
	
	List<Employee> queryForList(QueryObject qo);
	
	int queryForCount(QueryObject qo);
	
	
	List<Employee> listAll();
}
