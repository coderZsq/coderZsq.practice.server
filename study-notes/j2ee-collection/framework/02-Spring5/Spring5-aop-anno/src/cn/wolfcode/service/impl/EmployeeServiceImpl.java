package cn.wolfcode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.dao.IEmployeeDAO;
import cn.wolfcode.domain.Employee;

@Service
public class EmployeeServiceImpl {// implements IEmployeeService {

	@Autowired
	private IEmployeeDAO dao;

	public void save(Employee emp) {
		dao.save(emp);
		System.out.println("保存成功");
	}

	public void update(Employee emp) {
		dao.update(emp);
		throw new RuntimeException("故意错误的");
	}
}
