package com.sq.demo.mysqldemo;

import com.mysql.cj.jdbc.MysqlXAConnection;
import com.mysql.cj.jdbc.MysqlXid;

import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class XADemo {
    public static void main(String[] args) throws SQLException {
        boolean logXa = true; // 打印调试日志
        Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        // 创建支持XA协议的连接
        XAConnection xaConn1 = new MysqlXAConnection((com.mysql.cj.jdbc.JdbcConnection) conn1, logXa);
        // 获取到XAResource 资源 对XA协议的封装
        XAResource rm1 = xaConn1.getXAResource();
        Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        XAConnection xaConn2 = new MysqlXAConnection((com.mysql.cj.jdbc.JdbcConnection) conn2, logXa);
        XAResource rm2 = xaConn2.getXAResource();

        // 事务ID
        byte[] gtrid = "global_transaction_id".getBytes();
        int formatId = 1;
        Xid xid1 = null;
        Xid xid2 = null;
        try {
            // 创建一个分支id
            byte[] bqual1 = "branch_1".getBytes();
            xid1 = new MysqlXid(gtrid, bqual1, formatId);
            rm1.start(xid1, XAResource.TMNOFLAGS);
            PreparedStatement ps1 = conn1.prepareStatement("INSERT INTO user(name) VALUES ('coderZsq')");
            ps1.execute();
            rm1.end(xid1, XAResource.TMSUCCESS);

            byte[] bqual2 = "branch_2".getBytes();
            xid2 = new MysqlXid(gtrid, bqual2, formatId);
            // 执行rm2上的事务分支
            rm2.start(xid2, XAResource.TMNOFLAGS);
            PreparedStatement ps2 = conn2.prepareStatement("INSERT INTO user(name) VALUES ('Castie!')");
            ps2.execute();
            rm2.end(xid2, XAResource.TMSUCCESS);

            // ========================两阶段提交==============================
            int rm1_prepare = rm1.prepare(xid1);
            int rm2_prepare = rm2.prepare(xid2);
            if (rm1_prepare == XAResource.XA_OK && rm2_prepare == XAResource.XA_OK) { // 所有事务分支都prepare
                rm1.commit(xid1, false);
                int i = 1 / 0;
                rm2.commit(xid2, false);
            } else {
                // 如果有事务分支没有成功, 则回滚
                rm1.rollback(xid1);
                rm2.rollback(xid2);
            }
        } catch (XAException e) {
            // 回滚代码
            try {
                rm1.rollback(xid1);
                rm2.rollback(xid2);
            } catch (XAException xaException) {
                xaException.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
