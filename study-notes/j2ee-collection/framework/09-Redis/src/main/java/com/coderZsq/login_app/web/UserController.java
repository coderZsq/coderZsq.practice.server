package com.coderZsq.login_app.web;

import com.coderZsq.login_app.common.PageResult;
import com.coderZsq.login_app.domain.User;
import com.coderZsq.login_app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("login")
    public PageResult login(User user) throws Exception {
//        User u = userService.queryByName(user.getUsername());
        User u = userService.queryByNameByCache(user.getUsername());
        if(user.getPassword()!=null&& u !=null && user.getPassword().equals(u.getPassword())){
            return PageResult.success();
        }else{
            return PageResult.mark("用户名或者密码错误");
        }
    }
}
