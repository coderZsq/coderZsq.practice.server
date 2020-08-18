package com.sq.obj;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    private String driverClass;
    private String url;
    private String username;
    private String password;

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public static Connection getConn() throws Exception {
    //     Class.forName("com.mysql.jdbc.Driver");
    //     return DriverManager.getConnection("jdbc:mysql://localhost:3306/test_mybatis", "root", "root");
    // }

    public Connection getConn() throws Exception {
        Class.forName(driverClass);
        return DriverManager.getConnection(url, username, password);
    }
}
