package com.coderZsq.crm.shiro;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

public class ShiroFreeMarkerConfigurer extends FreeMarkerConfigurer {

    //在bean对象初始化完成以后会执行的赋值操作
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        //1 完成父类的初始化操作
        super.afterPropertiesSet();
        //2 把shiroTag添加到配置中
        Configuration configuration = this.getConfiguration();//获取配置对象
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}
