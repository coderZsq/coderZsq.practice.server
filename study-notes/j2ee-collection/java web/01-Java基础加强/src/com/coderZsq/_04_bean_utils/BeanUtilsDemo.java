package com.coderZsq._04_bean_utils;

import com.coderZsq._02_javabean.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.LongConverter;

import java.util.HashMap;
import java.util.Map;

// 测试使用commons-beanutils组件
public class BeanUtilsDemo {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        Map<String, Object> map = new HashMap<>();
        map.put("id", "");
        map.put("name", "张三");
        map.put("age", 11);
        map.put("bornDate", "2018-10-10");

        System.out.println(p);
        // 重新注册Long类型的转换器, 如果id没有值, 那么此时id设置为null, 而不是缺省的0
        ConvertUtils.register(new LongConverter(null), Long.class);
        // beanutils不支持String-->Date转换, 需要我们手动设置转换模式
        DateConverter dateConverter = new DateConverter(null);
        dateConverter.setPattern("yyyy-MM-dd");
        ConvertUtils.register(dateConverter, java.util.Date.class);
        BeanUtils.copyProperties(p, map);
        System.out.println(p);

        Person p2 = new Person();
        System.out.println(p2);

        BeanUtils.copyProperties(p2, p);
        System.out.println(p2);
    }
}
