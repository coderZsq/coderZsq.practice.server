package com.coderZsq._17_smis.dao.impl;

import com.coderZsq._17_smis.dao.IStudentDAO;
import com.coderZsq._17_smis.domain.Student;
import com.coderZsq._17_smis.handler.BeanHandler;
import com.coderZsq._17_smis.handler.BeanListHandler;
import com.coderZsq._17_smis.util.JdbcTemplate;

import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public void save(Student stu) {
        JdbcTemplate.update("INSERT INTO t_student (name,age) VALUES(?,?)",
                stu.getName(), stu.getAge());
    }

    @Override
    public void delete(Long id) {
        JdbcTemplate.update("DELETE FROM t_student WHERE id = ?", id);
    }

    @Override
    public void update(Long id, Student stu) {
        JdbcTemplate.update("UPDATE t_student SET name = ?, age = ? WHERE id = ?",
                stu.getName(), stu.getAge(), id);
    }

    @Override
    public Student get(Long id) {
        String sql = "SELECT * FROM t_student WHERE id = ?";
        return JdbcTemplate.query(sql, new BeanHandler<>(Student.class), id);
    }

    @Override
    public List<Student> listAll() {
        // 创建一个结果集处理: 每一行数据封装成Student对象
        // return JdbcTemplate.query("SELECT * FROM t_student", new StudentResultSetHandler());
        return JdbcTemplate.query("SELECT * FROM t_student", new BeanListHandler<>(Student.class));
    }
}

// 把结果集中每一行数据封装成Student对象
// class StudentResultSetHandler implements IResultSetHandler<List<Student>> {
//     @Override
//     public List<Student> handle(ResultSet rs) throws Exception {
//         List<Student> list = new ArrayList<>();
//         while (rs.next()) {
//             Student stu  = new Student();
//             stu.setId(rs.getLong("id"));
//             stu.setName(rs.getString("name"));
//             stu.setAge(rs.getInt("age"));
//             list.add(stu);
//         }
//         return list;
//     }
// }
