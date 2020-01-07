package cn.wolfcode.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.wolfcode.dao.IEmployeeDAO;
import cn.wolfcode.domain.Employee;

@Repository
public class EmployeeDAOImpl implements IEmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	public void save(Employee e) {
		jdbcTemplate.update("INSERT INTO employee (username,password,age,hiredate) VALUES (?,?,?,?)", e.getUsername(),
				e.getPassword(), e.getAge(), e.getHiredate());
	}

	public void update(Employee e) {
		jdbcTemplate.update("UPDATE employee SET username= ?,password=?,age = ?,hiredate=? WHERE id= ?",
				e.getUsername(), e.getPassword(), e.getAge(), e.getHiredate(), e.getId());
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM employee WHERE id = ?", id);
	}

	public Employee get(Long id) {
		List<Employee> list = jdbcTemplate.query("SELECT id,username,password,age,hiredate FROM employee WHERE id = ?",
				new Object[] { id }, (rs, rowNum) -> {
					Employee e = new Employee();
					e.setId(rs.getLong("id"));
					e.setUsername(rs.getString("username"));
					e.setPassword(rs.getString("password"));
					e.setHiredate(rs.getDate("hiredate"));
					e.setAge(rs.getInt("age"));
					return e;
				});
		return list.size() == 1 ? list.get(0) : null;
	}

	public List<Employee> listAll() {
		return jdbcTemplate.query("SELECT id,username,password,age,hiredate FROM employee", new Object[] {},
				(rs, rowNum) -> {
					Employee e = new Employee();
					e.setId(rs.getLong("id"));
					e.setUsername(rs.getString("username"));
					e.setPassword(rs.getString("password"));
					e.setAge(rs.getInt("age"));
					e.setHiredate(rs.getDate("hiredate"));
					return e;
				});
	}

	public Employee checkLogin(String username, String password) {
		List<Employee> list = jdbcTemplate.query(
				"SELECT id,username,password,age,hiredate FROM employee WHERE username = ? AND password = ?",
				new Object[] { username, password }, (rs, rowNum) -> {
					Employee e = new Employee();
					e.setId(rs.getLong("id"));
					e.setUsername(rs.getString("username"));
					e.setPassword(rs.getString("password"));
					e.setAge(rs.getInt("age"));
					e.setHiredate(rs.getDate("hiredate"));
					return e;
				});
		return list.size() == 1 ? list.get(0) : null;
	}
}
