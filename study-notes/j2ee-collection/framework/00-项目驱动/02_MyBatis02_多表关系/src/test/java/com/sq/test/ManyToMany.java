package com.sq.test;

import com.sq.bean.Job;
import com.sq.bean.Person;
import com.sq.util.MyBatises;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class ManyToMany {
    @Test
    public void personList() {
        try (SqlSession session = MyBatises.openSession()) {
            List<Person> persons = session.selectList("person.list");
            System.out.println(persons);
        }
    }

    @Test
    public void personGet() {
        try (SqlSession session = MyBatises.openSession()) {
            Person person = session.selectOne("person.get", 1);
            System.out.println(person);
        }
    }

    @Test
    public void jobList() {
        try (SqlSession session = MyBatises.openSession()) {
            List<Job> jobs = session.selectList("job.list");
            System.out.println(jobs);
        }
    }

    @Test
    public void bankCardGet() {
        try (SqlSession session = MyBatises.openSession()) {
            Job job = session.selectOne("job.get", 1);
            System.out.println(job);
        }
    }
}
