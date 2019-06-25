package com.coderZsq;

import java.util.HashMap;
import java.util.Map;

// 封装了 <action> 元素的所有信息
public class ActionConfig {
    private String name; // <action/>元素的name属性
    private String className; // <action/>元素的class属性
    private String method; // <action/>元素的method属性

    // <action>元素中具有多个<result>元素
    private Map<String, ResultConfig> resultConfigMap = new HashMap<>();

    public ActionConfig(String name, String className, String method) {
        this.name = name;
        this.className = className;
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
    }

    public Map<String, ResultConfig> getResultConfigMap() {
        return resultConfigMap;
    }

    @Override
    public String toString() {
        return "ActionConfig{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", method='" + method + '\'' +
                ", resultConfigMap=" + resultConfigMap +
                '}';
    }
}
