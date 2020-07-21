package com.sq;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Student extends Person<String, Integer> implements Test1<Integer, Double>, Test2<Double, Long, StringBuilder> {
    public void printGenericType() {
        // 父类
        ParameterizedType superClassType = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] superClassArgs = superClassType.getActualTypeArguments();
        for (Type arg : superClassArgs) {
            System.out.println(arg);
        }

        // 接口
        Type[] interfaceTypes = getClass().getGenericInterfaces();
        for (Type type : interfaceTypes) {
            ParameterizedType interfaceType = (ParameterizedType) type;
            Type[] interfaceArgs = interfaceType.getActualTypeArguments();
            for (Type arg : interfaceArgs) {
                System.out.println(arg);
            }
        }
    }
}
