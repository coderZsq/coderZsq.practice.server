package com.coderZsq._07_smis.dao.impl;

import com.coderZsq._07_smis.dao.IStudentDAO;
import com.coderZsq._07_smis.domain.Student;
import com.coderZsq.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public void save(Student stu) {
        String sql = "INSERT INTO t_student (name,age) VALUES(?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, stu.getName());
            ps.setInt(2, stu.getAge());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM t_student WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
    }

    @Override
    public void update(Long id, Student stu) {
        String sql = "UPDATE t_student SET name = ?, age = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            ps.setString(1, stu.getName());
            ps.setInt(2, stu.getAge());
            ps.setLong(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
    }

    @Override
    public Student get(Long id) {
        String sql = "SELECT * FROM t_student WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            // 设置占位符参数
            ps.setLong(1, id);
            rs = ps.executeQuery();
            // 处理结果集: 把每一行数据封装成一个对象
            if (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getLong("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                return stu;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
        return null;
    }

    @Override
    public List<Student> listAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM t_student";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            // 处理结果集: 把每一行数据封装成一个对象
            while (rs.next()) {
                Student stu = new Student();
                stu.setId(rs.getLong("id"));
                stu.setName(rs.getString("name"));
                stu.setAge(rs.getInt("age"));
                list.add(stu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
        return list;
    }
}
