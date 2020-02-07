package com.coderZsq._09_batch;

import com.coderZsq.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// 保存3000条数据
public class BatchTest {
    // Statement: 未使用批量处理
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

    // Statement: 使用批量处理
    @Test
    public void testSaveByStatement_batch() throws Exception {
        Connection conn = JdbcUtil.getConn();
        long begin = System.currentTimeMillis();
        Statement st = conn.createStatement();
        for (int i = 1; i < 3001; i++) {
            String sql = "INSERT INTO t_student (name,age) VALUES ('AA'," + i + ")";
            st.addBatch(sql); // 把SQL存储到批量操作中
            if (i % 200 == 0) {
                st.executeBatch(); // 执行批量操作
                st.clearBatch(); // 清楚上述批量操作
            }
        }
        System.out.println(System.currentTimeMillis() - begin);
    }

    // PreparedStatement: 未使用批量处理
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

    // PreparedStatement: 使用批量处理
    @Test
    public void testSaveByPreparedStatement_batch() throws Exception {
        Connection conn = JdbcUtil.getConn();
        long begin = System.currentTimeMillis();
        String sql = "INSERT INTO t_student (name,age) VALUES (?,?)"; // SQL模板
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 1; i < 3001; i++) {
            // 执行之前, 设置占位符参数值(设置第几个?具体的值是多少)
            ps.setString(1, "AA"); //  设置第1个?的值为AA
            ps.setInt(2, i); // 设置第2个?的值为12
            ps.addBatch(); // 添加进批处理中
            if (i % 200 == 0) {
                ps.executeBatch(); // 执行批量操作
                ps.clearBatch(); // 清楚缓存
                ps.clearParameters(); // 清楚参数
            }
        }
        System.out.println(System.currentTimeMillis() - begin);
    }
}
