package com.coderZsq._10_blob;

import com.coderZsq.util.JdbcUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BlobTest {
    // 把图片的数据存储到t_image表中
    @Test
    public void test1() throws Exception {
        String sql = "INSERT INTO t_image (img) VALUES (?)";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBlob(1, new FileInputStream("/Users/zhushuangquan/Desktop/blob.png"));
        ps.executeUpdate();
        JdbcUtil.close(conn, ps,  null);
    }

    @Test
    public void test2() throws Exception {
        String sql = "SELECT * FROM t_image WHERE id = ?";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, 1L);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Blob blob = rs.getBlob("img");
            InputStream in = blob.getBinaryStream();
            // 文件拷贝操作
            Files.copy(in, Paths.get("/Users/zhushuangquan/Desktop/blob2.png"));
        }
        JdbcUtil.close(conn, ps, rs);
    }
}
