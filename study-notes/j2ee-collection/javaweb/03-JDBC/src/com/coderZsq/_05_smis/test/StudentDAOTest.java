package com.coderZsq._05_smis.test;

import com.coderZsq._05_smis.dao.IStudentDAO;
import com.coderZsq._05_smis.dao.impl.StudentDAOImpl;
import com.coderZsq._05_smis.domain.Student;
import org.junit.Test;

import java.util.List;

// DAO组件的测试类, 所以得依赖DAO组件
public class StudentDAOTest {
    // 依赖DAO对象
    private IStudentDAO dao = new StudentDAOImpl();
    @Test
    public void testSave() {
        Student stu = new Student();
        stu.setName("三少爷");
        stu.setAge(27);
        dao.save(stu);
    }

    @Test
    public void testDelete() {
        dao.delete(2L);
    }

    @Test
    public void testUpdate() {
        Student stu = new Student();
        stu.setName("阿飞");
        stu.setAge(28);
        dao.update(6L, stu);
    }

    @Test
    public void testGet() {
        Student stu = dao.get(5L);
        System.out.println(stu);
    }

    @Test
    public void testListAll() {
        List<Student> stus = dao.listAll();
        for (Student stu : stus) {
            System.out.println(stu);
        }
    }
}
