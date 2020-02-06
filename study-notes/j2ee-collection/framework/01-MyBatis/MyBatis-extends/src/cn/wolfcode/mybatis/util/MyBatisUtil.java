package cn.wolfcode.mybatis.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory factory = null;
	static {
		try {
			//创建SqlSessionFactory对象
			factory = new SqlSessionFactoryBuilder().build(//
					Resources.getResourceAsStream("mybatis-config.xml")//
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//返回一个SqlSession对象
	public static SqlSession getSession() {
		return factory.openSession();
	}
}
