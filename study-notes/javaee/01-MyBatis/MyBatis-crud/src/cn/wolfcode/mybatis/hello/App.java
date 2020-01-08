package cn.wolfcode.mybatis.hello;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import cn.wolfcode.mybatis.util.MyBatisUtil;
import lombok.Cleanup;

public class App {

	
	//保存一条用户信息
	/*
	 在开发中,我们经常会有这样的一个需求:
	  	保存一条数据之后,需要得到刚刚保存数据生成的主键的值.
	 */
	@Test
	void testSave() throws Exception {
		User u  = new User();
		u.setName("楚留香");
		u.setSalary(new BigDecimal("800"));
		System.out.println(u);
		SqlSession session = MyBatisUtil.getSession();
		//具体操作
		session.insert("cn.wolfcode.mybatis.hello.UserMapper.save",u);
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

	
	
	
	
	
	
	
	
	
	
	
	private static Logger log = Logger.getLogger(App.class);

	@Test
	void testLog() throws Exception {
		//如果日志级别是INFO,就输出
		if (log.isInfoEnabled()) {
			log.info("银行转账操作");
		}
		if (log.isDebugEnabled()) {
			log.debug("查询数据库");
		}
		if (log.isTraceEnabled()) {
			log.trace("连接数据库");
		}
		if (log.isTraceEnabled()) {
			log.trace("获取连接对象");
		}
		if (log.isTraceEnabled()) {
			log.trace("执行SQL");
		}
		if (log.isDebugEnabled()) {
			log.debug("转账......");
		}

		if (log.isInfoEnabled()) {
			log.info("银行转账成功");
		}

	}

	//查询ID为1的用户信息
	@Test
	void testGet() throws Exception {
		//1:从classpath路径去加载MyBatis全局配置文件:mybatis-config.xml
		InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
		//2:创建SqlSessionFactory对象,好比是DataSource
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//3:创建SqlSession对象,好比是Connection
		SqlSession session = factory.openSession();
		//4:具体的操作(增删改查)
		User u = session.selectOne("cn.wolfcode.mybatis.hello.UserMapper.get", 2L);
		//5:关闭SqlSession
		session.close();
		System.out.println(u);
	}

	//查询所有的用户信息
	@Test
	void testListAll() throws Exception {
		try (SqlSession session = MyBatisUtil.getSession();) {
			//具体的操作(增删改查)
			List<User> us = session.selectList("cn.wolfcode.mybatis.hello.UserMapper.listAll");
			for (User u : us) {
				System.out.println(u);
			}
		}
	}

	//查询所有的用户信息
	@Test
	void testListAll2() throws Exception {
		@Cleanup
		SqlSession session = MyBatisUtil.getSession();
		List<User> us = session.selectList("cn.wolfcode.mybatis.hello.UserMapper.listAll");
		for (User u : us) {
			System.out.println(u);
		}
	}
}
