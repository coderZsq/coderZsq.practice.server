package com.sq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println("-----------------------");
        // --spring.config.name=sq --address=666 --age=777
        // for (String arg : args) {
        //     System.out.println(arg);
        // }
        // -Dspring.config.name=sq -Daddress=666 -Dage=777
        // System.getProperties().forEach((k, v) -> {
        //     System.out.println(k + "_" + v);
        // });
        SpringApplication.run(Application.class, args);
    }
}
