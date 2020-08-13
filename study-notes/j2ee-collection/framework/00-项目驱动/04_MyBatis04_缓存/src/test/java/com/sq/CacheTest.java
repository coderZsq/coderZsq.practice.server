package com.sq;

import com.sq.bean.Job;
import com.sq.util.MyBatises;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CacheTest {
    @Test
    public void firstLevel() {
        try (SqlSession session = MyBatises.openSession()) {
            Job job1 = session.selectOne("job.get", 1);
            System.out.println(job1);

            session.update("job.update", null);

            Job job2 = session.selectOne("job.get", 1);
            System.out.println(job2);
        }
    }

    @Test
    public void secondLevel() {
        try (SqlSession session = MyBatises.openSession()) {
            Job job = session.selectOne("job.get", 1);
            job.setName("666");
            System.out.println(job + "_" + job.getName());
        }

        try (SqlSession session = MyBatises.openSession()) {
            Job job = session.selectOne("job.get", 1);
            System.out.println(job + "_" + job.getName());
        }

        try (SqlSession session = MyBatises.openSession()) {
            Job job = session.selectOne("job.get", 1);
            System.out.println(job + "_" + job.getName());
        }

        try (SqlSession session = MyBatises.openSession()) {
            Job job = session.selectOne("job.get", 1);
            System.out.println(job + "_" + job.getName());
        }

        /*
            readOnly为true, 代表: 只读, 二级缓存中是: 原对象, 不安全
            readOnly为false, 代表: 可读写, 二级缓存中是: 原对象序列化后的结果, 安全
         */
    }

    @Test
    public void secondLevel2() {
        SqlSession session1 = MyBatises.openSession();
        Job job1 = session1.selectOne("job.get", 1);
        session1.close();
        job1.setName("666");
        System.out.println(job1 + "_" + job1.getName());

        SqlSession session2 = MyBatises.openSession();
        Job job2 = session2.selectOne("job.get", 1);
        System.out.println(job2 + "_" + job2.getName());
    }
}
