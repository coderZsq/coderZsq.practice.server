package com.coderZsq._13_smis.test;

import com.coderZsq._13_smis.dao.IStudentDAO;
import com.coderZsq._13_smis.dao.impl.StudentDAOImpl;
import com.coderZsq._13_smis.domain.Student;
import com.coderZsq._13_smis.handler.IResultSetHandler;
import com.coderZsq._13_smis.util.JdbcTemplate;
import org.junit.Test;

import java.sql.ResultSet;
import java.util.List;

public class StudentDAOTest {
    private IStudentDAO dao = new StudentDAOImpl();

    @Test
    public void testSave() {
        Student stu = new Student();
        stu.setName("陆小凤");
        stu.setAge(23);
        dao.save(stu);
    }

    @Test
    public void testDelete() {
        dao.delete(18029L);
    }

    @Test
    public void testUpdate() {
        Student stu = new Student();
        stu.setName("西门吹雪");
        stu.setAge(80);
        dao.update(18029L, stu);
    }

    @Test
    public void testGet() {
        Student stu = dao.get(18030L);
        System.out.println(stu);
    }

    @Test
    public void testListAll() {
        List<Student> list = dao.listAll();
        for (Student stu : list) {
            System.out.println(stu);
        }
    }

    // 查询t_student表中记录条数
    @Test
    public void testGetCount() throws Exception {
        String sql = "SELECT COUNT(id) FROM t_student";
        Long totalCount = JdbcTemplate.query(sql, new IResultSetHandler<Long>() {
            @Override
            public Long handle(ResultSet rs) throws Exception {
                if (rs.next()) {
                    return rs.getLong(1);

                }
                return 0L;
            }
        });
        System.out.println(totalCount);
    }
}
