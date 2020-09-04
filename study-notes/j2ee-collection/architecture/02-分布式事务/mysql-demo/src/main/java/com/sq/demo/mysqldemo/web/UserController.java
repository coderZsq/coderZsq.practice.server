package com.sq.demo.mysqldemo.web;

import com.sq.demo.mysqldemo.domain.User;
import com.sq.demo.mysqldemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/save")
    public String save(User user, String userName) {
        userService.save(user, userName);
        return "success";
    }
}
