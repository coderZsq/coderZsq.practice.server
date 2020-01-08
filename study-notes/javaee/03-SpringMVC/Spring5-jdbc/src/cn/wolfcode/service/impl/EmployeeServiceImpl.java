package cn.wolfcode.service.impl;

import java.util.List;

import cn.wolfcode.dao.IEmployeeDAO;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService {
	private IEmployeeDAO dao;

	public void setDao(IEmployeeDAO dao) {
		this.dao = dao;
	}

	public void save(Employee emp) {
		dao.save(emp);
	}

	public void update(Employee emp) {
		dao.update(emp);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public Employee get(Long id) {
		return dao.get(id);
	}

	public List<Employee> listAll() {
		return dao.listAll();
	}
}
