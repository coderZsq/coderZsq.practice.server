package com.coderZsq._08_tx;

import com.coderZsq.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TxTest {
    // 银行转账案例
    @Test
    public void test1() throws Exception {
        Connection conn = JdbcUtil.getConn();
        // ----------------检查张无忌账户余额---------------------
        String sql = "SELECT * FROM account WHERE name = ? AND balance >= ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, "张无忌");
        ps.setInt(2, 1000);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            throw new RuntimeException("余额不足!");
        }
        // -----------------减少张无忌1000--------------------
        sql = "UPDATE account SET balance = balance - ? WHERE  name = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, 1000);
        ps.setString(2, "张无忌");
        ps.executeUpdate();
        // 使用异常, 模拟停电
        int a = 1 / 0;
        // -----------------增加赵敏1000--------------------
        sql = "UPDATE account SET balance = balance + ? WHERE  name = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, 1000);
        ps.setString(2, "赵敏");
        ps.executeUpdate();
        JdbcUtil.close(conn, ps, rs);
    }

    // 处理事务
    @Test
    public void test2() {
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // ----------------检查张无忌账户余额---------------------
            String sql = "SELECT * FROM account WHERE name = ? AND balance >= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "张无忌");
            ps.setInt(2, 1000);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new RuntimeException("余额不足!");
            }
            conn.setAutoCommit(false); // 设置事务手动提交
            // -----------------减少张无忌1000--------------------
            sql = "UPDATE account SET balance = balance - ? WHERE  name = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1000);
            ps.setString(2, "张无忌");
            ps.executeUpdate();
            // 使用异常, 模拟停电
            int a = 1 / 0;
            // -----------------增加赵敏1000--------------------
            sql = "UPDATE account SET balance = balance + ? WHERE  name = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1000);
            ps.setString(2, "赵敏");
            ps.executeUpdate();

            conn.commit(); // 提交事务
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback(); // 回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
    }
}
