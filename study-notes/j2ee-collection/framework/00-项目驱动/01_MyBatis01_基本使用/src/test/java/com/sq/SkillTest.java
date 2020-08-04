package com.sq;

import com.sq.bean.Skill;
import com.sq.util.MyBatises;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

public class SkillTest {
    @Test
    public void select() throws Exception {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
            // 创建一个工厂构建器
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

            // 创建一个工厂
            SqlSessionFactory factory = builder.build(reader);

            // 创建一个Session
            try (SqlSession session = factory.openSession()) {
                // 执行SQL语句
                List<Skill> skills = session.selectList("skill.list");
                for (Skill skill : skills) {
                    System.out.println(skill);
                }
            }
        }
    }

    @Test
    public void select2() throws Exception {
        try (SqlSession session = MyBatises.openSession()) {
            Skill skill = session.selectOne("skill.get", 4);
            System.out.println(skill);
        }
    }

    @Test
    public void select3() throws Exception {
        try (SqlSession session = MyBatises.openSession()) {
            // Map<String, Object> map = new HashMap<>();
            // map.put("id", 3);
            // map.put("level", 2);
            // List<Skill> skills = session.selectList("skill.list2", map);

            Skill param = new Skill();
            param.setId(3);
            param.setLevel(5);
            param.setName("%J%");
            List<Skill> skills = session.selectList("skill.list2", param);
            for (Skill skill : skills) {
                System.out.println(skill);
            }
        }
    }
}

