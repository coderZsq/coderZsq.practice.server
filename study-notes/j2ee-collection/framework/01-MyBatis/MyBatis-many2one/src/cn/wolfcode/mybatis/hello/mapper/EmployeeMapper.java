package cn.wolfcode.mybatis.hello.mapper;

import java.util.List;

import cn.wolfcode.mybatis.hello.domain.Employee;

public interface EmployeeMapper {
	void save(Employee e);

	Employee get(Long id);

	List<Employee> listAll();
}
