package cn.wolfcode.wolf2w;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  //开启定时任务支持
public class MgrSite {
    public static void main(String[] args) {
        //解决netty冲突
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(MgrSite.class, args);
    }
}
