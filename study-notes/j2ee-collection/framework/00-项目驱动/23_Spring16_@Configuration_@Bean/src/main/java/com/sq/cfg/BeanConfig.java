package com.sq.cfg;

import com.sq.bean.DogFactoryBean;
import com.sq.domain.Person;
import com.sq.domain.Skill;
import com.sq.domain.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("skill.properties")
public class BeanConfig {
    // @Bean
    // public Dog dog() {
    //     return new Dog();
    // }

    // @Bean
    // public DogFactoryBean dog() {
    //     return new DogFactoryBean();
    // }

    @Value("${name}")
    private String name;
    @Value("${level}")
    private int level;

    @Bean
    public Skill skill() {
        Skill skill = new Skill();
        skill.setName(name);
        skill.setLevel(level);
        return skill;
    }

    @Bean
    public Person person(DogFactoryBean bean) throws Exception {
        Person person = new Person();
        person.setDog(bean.getObject());
        return person;
    }

    @Bean
    public Student student(DogFactoryBean bean) throws Exception {
        Student student = new Student();
        student.setDog(bean.getObject());
        return student;
    }

    // @Bean
    // public Student student() throws Exception {
    //     Student student = new Student();
    //     student.setDog(dog().getObject());
    //     return student;
    // }
}
