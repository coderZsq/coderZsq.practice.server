package com.coderZsq._06_preparedstatement;

import com.coderZsq.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// 预编译语句对象
public class PreparedStatementTest {
    // 保存一条数据
    @Test
    public void testSaveByStatement() throws Exception {
        String sql = "INSERT INTO t_student (name,age) VALUES ('AA',12)";
        Connection conn = JdbcUtil.getConn();
        long begin = System.currentTimeMillis();
        for (int i = 1; i < 3001; i++) {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
        }
        System.out.println(System.currentTimeMillis() - begin);
    }

    @Test
    public void testSaveByPreparedStatement() throws Exception {
        Connection conn = JdbcUtil.getConn();
        long begin = System.currentTimeMillis();
        for (int i = 1; i < 3001; i++) {
            String sql = "INSERT INTO t_student (name,age) VALUES (?,?)"; // SQL模板
            // ----------------------------------------
            PreparedStatement ps = conn.prepareStatement(sql);
            // 执行之前, 设置占位符参数值(设置第几个?具体的值是多少)
            ps.setString(1, "AA"); //  设置第1个?的值为AA
            ps.setInt(2, 12); // 设置第2个?的值为12
            ps.executeUpdate(); // 注意, 没有参数
            // ----------------------------------------
        }
        System.out.println(System.currentTimeMillis() - begin);
    }

    // 使用Statement完成登录操作
    @Test
    public void testLoginByStatement() throws Exception {
//        String sql = "SELECT * FROM t_student WHERE name = 'admin' AND password = '1234'";
        String sql = "SELECT * FROM t_student WHERE name = '' OR 1=1 OR '' AND password = '11'";
        Connection conn = JdbcUtil.getConn();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
        JdbcUtil.close(conn, st, rs);
    }

    // 使用PreparedStatement完成登录操作
    @Test
    public void testLoginPreparedStatement() throws Exception {
        String sql = "SELECT * FROM t_student WHERE name = ? AND password = ?";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "' OR 1=1 OR '");
        ps.setString(2, "22");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
        JdbcUtil.close(conn, ps, rs);
    }
}
