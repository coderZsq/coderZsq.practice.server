package com.coderZsq._01_shopping.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

// 表示把结果集中的一行数据, 封装成一个对象, 专门针对结果集中只有一行数据的情况
public class BeanHandler<T> implements IResultSetHandler<T> {
    private Class<T> classType; // 把结果集中的一行数据, 封装成什么类型的对象

    public BeanHandler(Class<T> classType) {
        this.classType = classType;
    }

    /*
    * 规范
    *   1: 规定表中的列名必须和对象中的属性名相同.
    *   2: 规定表中列名的类型必须和Java中的类型要匹配
    * */
    @Override
    public T handle(ResultSet rs) throws Exception {
        // 1): 创建对应类的一个对象
        T obj = classType.newInstance();
        // 2): 取出结果集中当前光标所在行的某一列的数据
        BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        if (rs.next()) {
            for (PropertyDescriptor pd : pds) {
                // 获取对象的属性名, 属性名和列名相同
                String columnName = pd.getName();
                rs.getObject(columnName);
                Object val = rs.getObject(columnName);
                // 3): 调用该对象的setter方法, 把某一列的数据, 设置进去
                pd.getWriteMethod().invoke(obj, val);
            }
        }
        return obj;
    }
}
