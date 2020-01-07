package cn.wolfcode.mybatis.hello;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import cn.wolfcode.mybatis.hello.domain.User;
import cn.wolfcode.mybatis.hello.mapper.UserMapper;
import cn.wolfcode.mybatis.proxy.MyMapperProxy;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class App {

	@Test
	void testMockMybatisProxy() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		MyMapperProxy<UserMapper> mapperProxy = new MyMapperProxy<UserMapper>();
		mapperProxy.setMapperInterface(UserMapper.class);
		mapperProxy.setSession(session);
		UserMapper proxy = mapperProxy.getProxyObject();
		//com.sun.proxy.$Proxy9
		System.out.println(proxy.getClass());
		
		List<User> list = proxy.listAll();
		
		for (User u : list) {
			System.out.println(u);
		}

	}

	//保存一条用户信息
	@Test
	void testSave() throws Exception {
		User u = new User();
		u.setName("楚留香");
		u.setSalary(new BigDecimal("800"));
		System.out.println(u);
		SqlSession session = MyBatisUtil.getSession();
		//具体操作
		session.insert("cn.wolfcode.mybatis.hello.UserMapper.save", u);
		//提交事务
		session.commit();
		//释放资源
		session.close();
		System.out.println(u);
	}

	//修改ID为4的用户信息
	@Test
	void testUpdate() throws Exception {
		User u = new User();
		u.setName("叶孤城");
		u.setSalary(new BigDecimal("900"));
		u.setId(4L);

		SqlSession session = MyBatisUtil.getSession();
		session.update("cn.wolfcode.mybatis.hello.UserMapper.update", u);
		//提交事务
		session.commit();
		session.close();
	}

	//删除ID为4的用户信息
	@Test
	void testDelete() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		//执行具体操作
		session.update("cn.wolfcode.mybatis.hello.UserMapper.delete", 4L);
		//提交事务
		session.commit();
		session.close();
	}

	//查询ID为1的用户信息
	@Test
	void testGet() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		//Mapper接口
		UserMapper userMapper = session.getMapper(UserMapper.class);
		User u = userMapper.get(1L);
		session.close();
		System.out.println(u);
	}

	//Mapper接口的原理:动态代理:Spring的时候重点来讲解
	//代理对象:com.sun.proxy.$Proxy9
	//查询所有的用户信息
	@Test
	void testListAll() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		//以前
		//List<User> us = session.selectList("cn.wolfcode.mybatis.hello.UserMapper.listAll");
		//Mapper接口
		UserMapper userMapper = session.getMapper(UserMapper.class);
		System.out.println(userMapper.getClass());
		List<User> us = userMapper.listAll();
		session.close();
		for (User u : us) {
			System.out.println(u);
		}
	}

}
