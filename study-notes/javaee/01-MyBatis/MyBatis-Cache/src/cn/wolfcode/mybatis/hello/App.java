package cn.wolfcode.mybatis.hello;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import cn.wolfcode.mybatis.hello.domain.Teacher;
import cn.wolfcode.mybatis.hello.mapper.TeacherMapper;
import cn.wolfcode.mybatis.util.MyBatisUtil;

public class App {

	//一级缓存:SqlSession级别
	@Test
	void testGet() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
		Teacher t1 = teacherMapper.get(1L);
		System.out.println(t1);
		System.out.println("-------------------------------------");
		session.clearCache();//清空一级缓存
		Teacher t2 = teacherMapper.get(1L);
		teacherMapper.get(1L);
		System.out.println(t2);
	}

	//二级缓存:mapper级别
	@Test
	void testSecondLevelCache() throws Exception {
		SqlSession session = MyBatisUtil.getSession();
		TeacherMapper mapper = session.getMapper(TeacherMapper.class);
		mapper.get(1L);
		session.close();
		System.out.println("-------------------------------------");
		Thread.sleep(3000);
		session = MyBatisUtil.getSession();
		mapper = session.getMapper(TeacherMapper.class);
		mapper.get(1L);
		session.close();
	}

}
