package com.coderzsq.demo;

import com.coderzsq._03_bean.AppConfig;
import com.coderzsq._03_bean.SomeBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class AppConfigTest {
    @Autowired
    private SomeBean someBean;
    
    @Test
    public void testBean() throws Exception {
        System.out.println("someBean = " + someBean);
    }
}
