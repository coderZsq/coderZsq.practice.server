package com.coderZsq;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Reference
    private IHelloService helloService;

    @RequestMapping("say")
    public String say(String name) {
        return helloService.greet(name);
    }
}
