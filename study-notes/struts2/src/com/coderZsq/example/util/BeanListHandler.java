package com.coderZsq.example.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeanListHandler<T> implements IResultSetHandler<List<T>> {
    private Class<T> targetType;

    public BeanListHandler(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public List<T> handle(ResultSet resultSet) throws Exception {
        List<T> list = new ArrayList<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(targetType, Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        while (resultSet.next()) {
            T obj = targetType.newInstance();
            for (PropertyDescriptor propertyDescriptor: propertyDescriptors) {
                String propName = propertyDescriptor.getName();
                Object val = resultSet.getObject(propName);
                propertyDescriptor.getWriteMethod().invoke(obj, val);
            }
            list.add(obj);
        }
        return list;
    }
}
