package com.sq.dp.designpattern.facade;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public <T> List<T> query(String sql, Class<T> clazz, String... params) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            pst = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int i = 1, len = params.length; i <= len; i++) {
                    pst.setObject(i, params[i]);
                }
            }
            rs = pst.executeQuery();
            List<T> result = new ArrayList<>();
            while (rs.next()) {
                BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                T obj = clazz.newInstance();
                for (PropertyDescriptor pd : pds) {
                    Method method = pd.getWriteMethod();
                    String methodName = method.getName();
                    method.invoke(obj, rs.getObject(methodName));
                }
                result.add(obj);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn, pst, rs);
        }
        return null;
    }
}
