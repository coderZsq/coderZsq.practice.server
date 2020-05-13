package com.coderZsq.redis._09_login_app;

import com.coderZsq.login_app.domain.User;
import com.coderZsq.login_app.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginAppApplicationTests {

    @Autowired
    private IUserService userService;

    @Test
    public void insert() throws Exception {
        for (int i = 0; i < 10000; i++) {
            User user=new User();
            user.setPassword("1");
            user.setUsername("user"+i);
            userService.insert(user);
        }
    }



}
