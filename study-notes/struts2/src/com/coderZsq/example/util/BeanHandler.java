package com.coderZsq.example.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

public class BeanHandler<T> implements IResultSetHandler<T> {

    private Class<T> targetType;

    public BeanHandler(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public T handle(ResultSet resultSet) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(targetType, Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        if (resultSet.next()) {
            T obj = targetType.newInstance();
            for (PropertyDescriptor propertyDescriptor: propertyDescriptors) {
                String propName = propertyDescriptor.getName();
                Object val = resultSet.getObject(propName);
                propertyDescriptor.getWriteMethod().invoke(obj, val);
            }
            return obj;
        }
        return null;
    }
}
