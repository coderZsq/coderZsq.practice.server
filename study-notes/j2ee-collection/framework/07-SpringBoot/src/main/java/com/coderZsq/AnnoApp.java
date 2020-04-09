package com.coderZsq;

import com.coderZsq.domain.DataSource;
import com.coderZsq.domain.anno.BoyFriend;
import com.coderZsq.domain.xml.GirlFriend;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnoApp {
    public static void main(String[] args) throws Exception {
        // 1. 加载配置类, 创建一个IoC容器
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        // 2. 从容器中获取bean对象
        GirlFriend lucy = ctx.getBean(GirlFriend.class);
        System.out.println(lucy);
        // 3. 从容器中获取扫描包路径创建的对象
        BoyFriend jack = ctx.getBean(BoyFriend.class);
        System.out.println(jack);
        DataSource ds = ctx.getBean(DataSource.class);
        System.out.println("ds = " + ds);
        ctx.destroy();
    }
}
