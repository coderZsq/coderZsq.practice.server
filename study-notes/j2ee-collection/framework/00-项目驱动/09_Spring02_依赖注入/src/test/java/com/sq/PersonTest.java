package com.sq;

import com.sq.domain.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedHashSet;
import java.util.Set;

public class PersonTest {
    @Test
    public void test() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = ctx.getBean("person", Person.class);
        // System.out.println(person);
    }

    @Test
    public void testSet() {
        Set<String> set = new LinkedHashSet<>();
        set.add("Tom");
        set.add("Jake");
        set.add("Rose");
        set.add("Jim");
        for (String s : set) {
            System.out.println(s);
        }
    }
}
