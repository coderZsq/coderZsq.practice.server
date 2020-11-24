package com.sq.mvc.controller;

import com.sq.domain.Car;
import com.sq.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class XMLController {

    /*
        <person name="Jack" age="20">
            <car name="BMW" price="123"/>
        </person>
     */

    @RequestMapping(value = "/xml1", produces = "application/xml; charset=UTF-8")
    @ResponseBody
    public String xml1() {
        Person person = new Person();
        person.setName("中文");
        person.setAge(20);

        Car car = new Car();
        car.setName("Bently");
        car.setPrice(100);
        person.setCar(car);

        // 用第三方库将Model对象转为XML字符串 (很多这样的库)

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<person name=\"" + person.getName() + "\" age=\"" + person.getAge() + "\">"
                + "<car name=\"" + car.getName() + "\" price=\"" + car.getPrice() + "\"/>"
                + "</person>";
    }

    @RequestMapping(value = "/xml2")
    @ResponseBody
    public Person xml2() {
        Person person = new Person();
        person.setName("Jack");
        person.setAge(20);

        Car car = new Car();
        car.setName("Bently");
        car.setPrice(100);
        person.setCar(car);

        return person;
    }
}
