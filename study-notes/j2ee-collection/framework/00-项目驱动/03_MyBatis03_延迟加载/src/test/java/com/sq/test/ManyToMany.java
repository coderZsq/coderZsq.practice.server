package com.sq.test;

import com.sq.bean.Job;
import com.sq.bean.Person;
import com.sq.util.MyBatises;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class ManyToMany {
    @Test
    public void personGet() {
        try (SqlSession session = MyBatises.openSession()) {
            Person person = session.selectOne("person.get", 1);
            // System.out.println(person.getBankCards());

            // System.out.println(new Person());
            // com.com.sq.bean.Person_$$_jvst4a5_0@384ad17b
            // com.com.sq.bean.Person@6e171cd7
        }
    }
}
