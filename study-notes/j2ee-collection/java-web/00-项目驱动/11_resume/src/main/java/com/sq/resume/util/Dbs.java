package com.sq.resume.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class Dbs {
    private static JdbcTemplate tpl;
    static {
        try (InputStream is = Dbs.class.getClassLoader().getResourceAsStream("druid.properties")){
            // 获取链接池
            Properties properties = new Properties();
            properties.load(is);
            DataSource ds = DruidDataSourceFactory.createDataSource(properties);
            // 创建tpl
            tpl = new JdbcTemplate(ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JdbcTemplate getTpl() {
        return tpl;
    }
}
