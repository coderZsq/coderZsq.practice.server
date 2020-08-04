package com.sq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) throws  Exception {
        Connection conn = DriverManager.getConnection("", "", "");
        try {
            // 开启事务(不要自动提交事务)
            conn.setAutoCommit(false);

            PreparedStatement stmt1 = conn.prepareStatement("UPDATE....");
            stmt1.executeUpdate();

            Integer nun = null;
            nun.intValue();

            PreparedStatement stmt2 = conn.prepareStatement("UPDATE....");
            stmt2.executeUpdate();
            // 结束事务
            // 提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 结束事务
            // 回滚事务
            if (conn != null) {
                conn.rollback();
            }
        }

    }
}
