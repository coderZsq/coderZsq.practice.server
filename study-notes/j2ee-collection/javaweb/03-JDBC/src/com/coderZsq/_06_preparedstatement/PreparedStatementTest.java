package com.coderZsq._06_preparedstatement;

import com.coderZsq.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

// 预编译语句对象
public class PreparedStatementTest {
    // 保存一条数据
    @Test
    public void testSaveByStatement() throws Exception {
        String sql = "INSERT INTO t_student (name,age) VALUES ('AA',12)";
        Connection conn = JdbcUtil.getConn();
        Statement st = conn.createStatement();
        st.executeUpdate(sql);
        JdbcUtil.close(conn, st, null);
    }

    @Test
    public void testSaveByPreparedStatement() throws Exception {
        String sql = "INSERT INTO t_student (name,age) VALUES (?,?)"; // SQL模板
        Connection conn = JdbcUtil.getConn();
        // ----------------------------------------
        PreparedStatement ps = conn.prepareStatement(sql);
        // 执行之前, 设置占位符参数值(设置第几个?具体的值是多少)
        ps.setString(1, "AA"); //  设置第1个?的值为AA
        ps.setInt(2, 12); // 设置第2个?的值为12
        ps.executeUpdate(); // 注意, 没有参数
        // ----------------------------------------
        JdbcUtil.close(conn, ps, null);
    }
}
