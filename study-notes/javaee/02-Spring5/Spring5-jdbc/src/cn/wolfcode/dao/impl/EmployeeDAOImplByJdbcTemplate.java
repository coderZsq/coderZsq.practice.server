package cn.wolfcode.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import cn.wolfcode.dao.IEmployeeDAO;
import cn.wolfcode.domain.Employee;

public class EmployeeDAOImplByJdbcTemplate implements IEmployeeDAO {
	private JdbcTemplate jdbcTemplate;

	//属性:dataSource
	public void setDataSource(DataSource ds) {
		this.jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings("unchecked")
	public void save(Employee emp) {
		jdbcTemplate.update("INSERT INTO employee (name,age) VALUES (?,?)",
				emp.getName(), emp.getAge());
		
		
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
		namedParameterJdbcTemplate.update("INSERT INTO employee (name,age) VALUES (:ename,:eage)", new HashMap() {{
			this.put("ename", emp.getName());
			this.put("eage", emp.getAge());
		}});
		
	}

	public void update(Employee emp) {
		jdbcTemplate.update("UPDATE employee SET name = ?,age = ? WHERE id= ?", emp.getName(), emp.getAge(),
				emp.getId());
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM employee WHERE id = ?", id);
	}

	public Employee get(Long id) {
		List<Employee> list = jdbcTemplate.query("SELECT id,name,age FROM employee WHERE id = ?", new Object[] { id },
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
		return jdbcTemplate.query("SELECT id,name,age FROM employee", new Object[] {}, new RowMapper<Employee>() {
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
