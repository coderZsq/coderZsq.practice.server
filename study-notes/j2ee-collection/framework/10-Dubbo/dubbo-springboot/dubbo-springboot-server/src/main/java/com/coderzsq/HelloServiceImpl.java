package com.coderzsq;

import com.coderZsq.IHelloService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl implements IHelloService {
    @Override
    public String greet(String name) {
        System.out.println("name = " + name);
        return "hello:" + name;
    }
}
