package cn.wolfcode.service.impl;

import java.util.List;

import cn.wolfcode.dao.IEmployeeDAO;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.util.UserContext;

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

	public void login(String username, String password) {
		Employee current =  dao.checkLogin(username,password);
		if(current==null) {
			throw new RuntimeException("账号或密码错误!");
		}
		UserContext.setCurrentUser(current);
	}
}
