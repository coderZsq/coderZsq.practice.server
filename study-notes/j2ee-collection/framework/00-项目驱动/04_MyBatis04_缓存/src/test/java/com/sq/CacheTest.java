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
            Job job1 = session.selectOne("job.get", 1);
            System.out.println(job1);
        }

        try (SqlSession session = MyBatises.openSession()) {
            Job job1 = session.selectOne("job.get", 1);
            System.out.println(job1);
        }

        try (SqlSession session = MyBatises.openSession()) {
            Job job1 = session.selectOne("job.get", 1);
            System.out.println(job1);
        }

        try (SqlSession session = MyBatises.openSession()) {
            Job job1 = session.selectOne("job.get", 1);
            System.out.println(job1);
        }

        /*
            readOnly为true, 代表: 只读, 二级缓存中是: 原对象, 不安全
            readOnly为false, 代表: 可读写, 二级缓存中是: 原对象序列化后的结果, 安全
         */
    }
}
