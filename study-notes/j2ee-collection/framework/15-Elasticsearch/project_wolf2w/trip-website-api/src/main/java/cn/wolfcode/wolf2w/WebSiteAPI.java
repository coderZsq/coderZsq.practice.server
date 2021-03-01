package cn.wolfcode.wolf2w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSiteAPI {

    public static void main(String[] args) {
        //解决netty冲突
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(WebSiteAPI.class, args);
    }
}
