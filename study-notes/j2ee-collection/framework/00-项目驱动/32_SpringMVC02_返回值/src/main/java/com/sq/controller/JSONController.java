package com.sq.controller;

import com.sq.domain.Dog;
import com.sq.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class JSONController {
    @RequestMapping(value = "/json1", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String json1() {
        // Person person = new Person();
        // person.setName("Jack");
        // person.setAge(20);
        //
        // Car car = new Car();
        // car.setName("Bently");
        // car.setPrice(100);
        // person.setCar(car);

        // 用第三方库将Model对象转为XML字符串 (很多这样的库)

        return "{\"name\":\"中文\",\"age\":20,\"car\":{\"name\":\"Bently\",\"price\":100}}";
    }

    @RequestMapping(value = "/json2")
    @ResponseBody
    public Student json2() {
        Student student = new Student();
        student.setName("Jack");
        student.setAge(20);
        student.setNickNames(List.of("123", "456", "789"));

        Dog dog = new Dog();
        dog.setName("Bently");
        dog.setPrice(100);
        student.setDog(dog);

        return student;
    }

    /*
        {
            "name": "Jack",
            "age": 20,
            "car": {
                "name": "Bently",
                "price": 100
            }
        }

        {"name":"Jack","age":20,"car":{"name":"Bently","price":100}}

        <person name="Jack" age="20"><car name="BMW" price="123"/></person>
     */
}
