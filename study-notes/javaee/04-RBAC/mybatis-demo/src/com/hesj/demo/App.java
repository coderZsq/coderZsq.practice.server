package com.hesj.demo;

import com.hesj.demo.domain.Department;
import com.hesj.demo.mapper.DepartmentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
public class App {

    @Test
    public void testGet() throws Exception {
        //1 获取构建起方法
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = sessionFactory.openSession();//开启会话
        //获取mapper接口
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
        Department department = departmentMapper.selectByPrimarykey(1L);
        System.out.println(department);
    }

    @Test
    public void testDelete() throws Exception {
        //1 获取构建起方法
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = sessionFactory.openSession();//开启会话 默认情况 手动管理事务
        DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
        departmentMapper.delete(1L);
        //提交事务
        session.commit();
    }
}
