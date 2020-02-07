package com.coderZsq._14_hibernate.util;

import com.coderZsq._14_hibernate.handler.IResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Jdbc操作的模板类
public class JdbcTemplate {
    /**
     * 查询操作(DQL)模板
     * 如果查询多个学生, 返回一个List<Student>合理
     * 查询单个学生, 也可以存储在List<Student>中
     *
     * @param sql    DQL操作的SQL模板(带有占位符?)
     * @param params SQL模板中?对应的参数值
     * @return List集合
     */
    public static <T> T query(String sql, IResultSetHandler<T> rsh, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            // 设置占位符参数
            for (int index = 0; index < params.length; index++) {
                ps.setObject(index + 1, params[index]);
            }
            rs = ps.executeQuery();
            // 处理结果集: 把每一行数据封装成一个对象
            return rsh.handle(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        throw new RuntimeException("查询操作有错");
    }

    /**
     * DML操作(增删改)的模板
     *
     * @param sql    DML操作的SQL模板(带有占位符?)
     * @param params SQL模板中?对应的参数值
     * @return 受影响的行数
     */
    public static int update(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConn();
            ps = conn.prepareStatement(sql);
            // 设置占位符参数
            for (int index = 0; index < params.length; index++) {
                // setObject方法: 第一个参数是第几个?, 第二个参数是占位符参数值
                ps.setObject(index + 1, params[index]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
        return 0;
    }
}
