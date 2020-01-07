package cn.wolfcode.mybatis.hello.service.impl;

import java.util.Collections;
import java.util.List;

import cn.wolfcode.mybatis.hello.domain.Employee;
import cn.wolfcode.mybatis.hello.mapper.EmployeeMapper;
import cn.wolfcode.mybatis.hello.query.PageResult;
import cn.wolfcode.mybatis.hello.query.QueryObject;
import cn.wolfcode.mybatis.hello.service.IEmployeeService;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class EmployeeServiceImpl implements IEmployeeService {

	private EmployeeMapper employeeMapper = MyBatisUtil.getMapper(EmployeeMapper.class);

	public PageResult query(QueryObject qo) {
		int rows = employeeMapper.queryForCount(qo);
		if (rows == 0) {
			return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
		}
		List<Employee> result = employeeMapper.queryForList(qo);
		return new PageResult(result, rows, qo.getCurrentPage(), qo.getPageSize());
	}

}
