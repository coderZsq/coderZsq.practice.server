package com.coderZsq._12_datasource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 使用DBCP连接池
public class DBCPTest {
    // 创建一个连接池对象
    public DataSource getDataSource() {
        // 创建连接对象

        BasicDataSource ds = new BasicDataSource();
        // 设置连接数据库的四要素
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/jdbc");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setMaxActive(5); // 最大连接数
        return ds;
    }

    @Test
    public void test1() throws Exception {
//        DataSource ds = this.getDataSource();
//        Connection conn = ds.getConnection();
//        Connection conn = DBCPUtil.getConn();
        Connection conn = DruidUtil.getConn();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM t_student");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getLong("id"));
        }
    }
}
