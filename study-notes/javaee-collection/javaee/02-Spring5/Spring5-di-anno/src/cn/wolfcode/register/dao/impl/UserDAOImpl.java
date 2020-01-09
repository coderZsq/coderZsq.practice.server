package cn.wolfcode.register.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.wolfcode.register.dao.IUserDAO;
import cn.wolfcode.register.domain.User;
import lombok.Cleanup;
import lombok.SneakyThrows;

@Repository
public class UserDAOImpl implements IUserDAO {

	@Autowired
	private DataSource dataSource;

	@SneakyThrows
	public void save(User u) {
		System.out.println("保存操作");
		@Cleanup
		Connection conn = dataSource.getConnection();
		String sql = "INSERT INTO user(name,age) VALUES(?,?)";
		@Cleanup
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, u.getName());
		ps.setInt(2, u.getAge());
		ps.executeUpdate();
	}
}
