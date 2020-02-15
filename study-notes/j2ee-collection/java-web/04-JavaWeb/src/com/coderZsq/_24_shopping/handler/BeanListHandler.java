package com.coderZsq._24_shopping.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// 表示把结果集中的多行数据, 封装成一个对象的集合<List<XX>>, 针对于结果集中有多行数据的
public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
    private Class<T> classType; // 把结果集中的一行数据, 封装成什么类型的对象

    public BeanListHandler(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public List<T> handle(ResultSet rs) throws Exception {
        List<T> list = new ArrayList<>();
        while (rs.next()) {
            // 每一行. 封装成一个对象
            // 1): 创建对应类的一个对象
            T obj = classType.newInstance();
            // 2): 取出结果集中当前光标所在行的某一列的数据
            list.add(obj);
            BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pd : pds) {
                // 获取对象的属性名, 属性名和列名相同
                String columnName = pd.getName();
                rs.getObject(columnName);
                Object val = rs.getObject(columnName);
                // 3): 调用该对象的setter方法, 把某一列的数据, 设置进去
                pd.getWriteMethod().invoke(obj, val);
                // 把每一行对应的对象, 存储到List集合中
            }
        }
        return list;
    }
}
