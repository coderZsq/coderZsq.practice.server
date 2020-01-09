package cn.wolfcode.dao.impl;

import cn.wolfcode.dao.IEmployeeDAO;
import cn.wolfcode.domain.Employee;

public class EmployeeDAOImpl implements IEmployeeDAO {

	public void save(Employee emp) {
		System.out.println("保存员工");
	}

	public void update(Employee emp) {
		System.out.println("修改员工");
	}
}
