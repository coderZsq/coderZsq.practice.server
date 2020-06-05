package com.coderZsq;

import lombok.Data;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Protocol;

@Data
public class Rabbit implements IRun {
    private Protocol protocol;
    @Override
    public void run(URL url) {
        System.out.println("url = " + url);
        System.out.println("rabbit... run");
        System.out.println("protocol = " + protocol);
    }
}
