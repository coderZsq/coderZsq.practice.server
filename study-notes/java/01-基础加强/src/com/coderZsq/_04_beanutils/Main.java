package com.coderZsq._04_beanutils;

import com.coderZsq._02_javabean.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        Person person = new Person();
        Map<String, Object> map = new HashMap<>();
        map.put("id", "");
        map.put("name", "Castie!");
        map.put("age", 18);
        map.put("bornDate", "2019-06-04");
        System.out.println(person);

        ConvertUtils.register(new LongConverter(null), Long.class);
        DateConverter dateConverter = new DateConverter(null);
        ConvertUtils.register(dateConverter, Date.class);
        dateConverter.setPattern("yyyy-MM-dd");

        BeanUtils.copyProperties(person, map);
        System.out.println(person);

        Person person1 = new Person();
        System.out.println(person1);
        BeanUtils.copyProperties(person1, person);
        System.out.println(person1);
    }
}
