package com.coderZsq._11_return_pk;

import com.coderZsq.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// 获取自动生成的主键
public class ReturnPkTest {
    // Statement方式
    @Test
    public void testStatement() throws Exception {
        String sql = "INSERT INTO t_student (name, age) VALUES ('AA', 23)";
        Connection conn = JdbcUtil.getConn();
        Statement st = conn.createStatement();
        // ----------------------------------
        // 设置可以获取自动生成的主键
        st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        // 去获取自动生成的主键
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            Long id = rs.getLong(1); // 获取第1列
            System.out.println(id);
        }
        // ----------------------------------
        JdbcUtil.close(conn, st,  null);
    }

    // PreparedStatement方式
    @Test
    public void testPrepareStatement() throws Exception {
        String sql = "INSERT INTO t_student (name, age) VALUES (?, ?)";
        Connection conn = JdbcUtil.getConn();
        // ----------------------------------
        // 设置可以获取自动生成的主键
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, "XX");
        ps.setInt(2, 12);
        ps.executeUpdate();
        // 去获取自动生成的主键
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            Long id = rs.getLong(1); // 获取第1列
            System.out.println(id);
        }
        // ----------------------------------
        JdbcUtil.close(conn, ps, null);
    }
}
