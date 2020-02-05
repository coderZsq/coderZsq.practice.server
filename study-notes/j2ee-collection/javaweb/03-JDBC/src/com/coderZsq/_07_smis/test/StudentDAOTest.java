package com.coderZsq._07_smis.test;

import com.coderZsq._07_smis.dao.IStudentDAO;
import com.coderZsq._07_smis.dao.impl.StudentDAOImpl;
import com.coderZsq._07_smis.domain.Student;
import org.junit.Test;

import java.util.List;

public class StudentDAOTest {
    private IStudentDAO dao = new StudentDAOImpl();

    @Test
    public void testSave() {
        Student stu = new Student();
        stu.setName("ABC");
        stu.setAge(23);
        dao.save(stu);
    }

    @Test
    public void testDelete() {
        dao.delete(10L);
    }

    @Test
    public void testUpdate() {
        Student stu = new Student();
        stu.setName("XYZ");
        stu.setAge(26);
        dao.update(10L, stu);
    }

    @Test
    public void testGet() {
        Student stu = dao.get(1L);
        System.out.println(stu);
    }

    @Test
    public void testListAll() {
        List<Student> list = dao.listAll();
        for (Student stu : list) {
            System.out.println(stu);
        }
    }
}
