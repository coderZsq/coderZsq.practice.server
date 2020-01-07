package cn.wolfcode.mybatis.hello.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.mybatis.hello.domain.Employee;

public interface EmployeeMapper {
	List<Employee> query(//
			@Param("minSalary") BigDecimal minSalary, //
			@Param("maxSalary") BigDecimal maxSalary, //
			@Param("deptId") Long deptId);

	void batchDelete(@Param("ids") List<Long> ids);

	void batchSave(@Param("emps") List<Employee> emps);
}
