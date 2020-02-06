package com.coderZsq._14_hibernate.util;

import com.coderZsq._14_hibernate.domain.Student;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

// 模拟Hibernate
public class HibernateMock {
    // 模拟保存操作
    // 内省机制: 可以获取和操作JavaBean对象的属性
    public static void save(Object obj) {
        try {
            // INSERT INTO t_student (name, age) VALUE (?, ?)
            // 获取对象对应的表名
            String tableName = obj.getClass().getSimpleName();
            Table table = obj.getClass().getAnnotation(Table.class);
            if (table != null) {
                tableName = table.value();
            }
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(tableName).append(" (");

            StringBuilder columnSql = new StringBuilder(); // 拼需要插入哪一些列的SQL: name,age
            StringBuilder placeHolderSql = new StringBuilder(); // 拼占位符的SQL: ?,?

            List<Object> params = new ArrayList<>(); // 装参数
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                // 对象中的属性名
                String propertyName = pd.getName();
                if (!"id".equals(propertyName)) {
                    columnSql.append(propertyName).append(",");
                    placeHolderSql.append("?").append(",");
                    // 获取属性的值, 调用属性的getter方法
                    Object val = pd.getReadMethod().invoke(obj);
                    params.add(val);
                }
            }
            // 删除最后的一个,
            columnSql.deleteCharAt(columnSql.length() - 1); // age,id,name
            placeHolderSql.deleteCharAt(placeHolderSql.length() - 1);
            sql.append(columnSql);
            sql.append(") VALUE (");
            sql.append(placeHolderSql);
            sql.append(")");
            System.out.println("SQL=" + sql);
            System.out.println("params=" + params);
            JdbcTemplate.update(sql.toString(), params.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Student stu = new Student("小溪", 17);
        HibernateMock.save(stu);
    }
}
