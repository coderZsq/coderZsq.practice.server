package com.sq.mvc.controller;

import com.sq.domain.Cat;
import com.sq.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties({Student.class, Cat.class})
public class TestController {
    // @Value("${name}")
    // private String name;
    // @Autowired
    // private Person person;
    // @Autowired
    // private Student student;

    @Autowired
    private Cat cat;

    // @Bean
    // @ConfigurationProperties("person")
    // public Person person() {
    //     return new Person();
    // }

    @GetMapping("/test")
    public String test() {

        // for (Method method : Person.class.getMethods()) {
        //     System.out.println(method.getName());
        // }

        // System.out.println(person);
        // System.out.println(student);
        System.out.println(cat);
        return "test!!!";
    }
}
