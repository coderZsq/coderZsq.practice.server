package cn.wolfcode.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import cn.wolfcode.dao.IEmployeeDAO;
import cn.wolfcode.domain.Employee;

public class EmployeeDAOImpl extends JdbcDaoSupport implements IEmployeeDAO {

	public void save(Employee emp) {
		super.getJdbcTemplate().update("INSERT INTO employee (name,age) VALUES (?,?)", emp.getName(), emp.getAge());
	}

	public void update(Employee emp) {
		super.getJdbcTemplate().update("UPDATE employee SET name = ?,age = ? WHERE id= ?", emp.getName(), emp.getAge(),
				emp.getId());
	}

	public void delete(Long id) {
		super.getJdbcTemplate().update("DELETE FROM employee WHERE id = ?", id);
	}

	public Employee get(Long id) {
		List<Employee> list = super.getJdbcTemplate().query("SELECT id,name,age FROM employee WHERE id = ?", new Object[] { id },
				(rs, rowNum) -> {
					Employee e = new Employee();
					e.setId(rs.getLong("id"));
					e.setName(rs.getString("name"));
					e.setAge(rs.getInt("age"));
					return e;
				});
		return list.size() == 1 ? list.get(0) : null;
	}

	public List<Employee> listAll() {
		return super.getJdbcTemplate().query("SELECT id,name,age FROM employee", new Object[] {}, new RowMapper<Employee>() {
			//把每一行结果集映射成一个Employee对象
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee e = new Employee();
				e.setId(rs.getLong("id"));
				e.setName(rs.getString("name"));
				e.setAge(rs.getInt("age"));
				return e;
			}
		});
	}
}
