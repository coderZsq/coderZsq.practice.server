package com.sq.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MyBatises {
    private static SqlSessionFactory factory;

    static {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            // 创建一个工厂构建器
            // SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            // 创建一个工厂
            factory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSession() {
        return factory.openSession();
    }
}
