package cn.wolfcode.mybatis.hello;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import cn.wolfcode.mybatis.hello.domain.User;
import cn.wolfcode.mybatis.hello.mapper.UserMapper;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class App {

	//保存一条用户信息
	@Test
	void testSave() throws Exception {
		User u = new User();
		u.setName("楚留香");
		u.setSalary(new BigDecimal("800"));

		System.out.println(u);
		SqlSession session = MyBatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		userMapper.save(u);
		session.commit();
		session.close();
		System.out.println(u);
	}

	//修改ID为4的用户信息
	@Test
	void testUpdate() throws Exception {
		User u = new User();
		u.setName("阿飞");
		u.setSalary(new BigDecimal("900"));
		u.setId(9L);

		SqlSession session = MyBatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		userMapper.update(u);
		session.commit();
		session.close();
	}

	//删除ID为4的用户信息
	@Test
	void testDelete() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		userMapper.delete(9L);
		session.commit();
		session.close();
	}

	//查询ID为1的用户信息
	@Test
	void testGet() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		User u = userMapper.get(1L);
		session.close();
		System.out.println(u);
	}

	@Test
	void testListAll() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		List<User> us = userMapper.listAll();
		session.close();
		for (User u : us) {
			System.out.println(u);
		}
	}

}
