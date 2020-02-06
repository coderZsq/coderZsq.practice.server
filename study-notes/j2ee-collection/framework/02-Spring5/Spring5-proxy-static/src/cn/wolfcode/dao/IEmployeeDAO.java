package cn.wolfcode.dao;

import cn.wolfcode.domain.Employee;

public interface IEmployeeDAO {

	void save(Employee emp);

	void update(Employee emp);
}
