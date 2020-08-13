package com.sq;

import com.sq.bean.Person;
import com.sq.dao.PersonDao;
import com.sq.util.MyBatises;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class PersonTest {
    @Test
    public void get() {
        try (SqlSession session = MyBatises.openSession()) {
            PersonDao dao = session.getMapper(PersonDao.class);
            Person person = dao.get(1);
            person.getBankCards();
        }
    }

    @Test
    public void list() {
        try (SqlSession session = MyBatises.openSession()) {
            PersonDao dao = session.getMapper(PersonDao.class);
            System.out.println(dao.list());
        }
    }
}
