package com.coderZsq.crm.web.controller;

import com.alibaba.fastjson.JSON;
import com.coderZsq.crm.common.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class UserController {
    // 在登录失败的时候, return true --> 放行 ---> 目标方法
    // login.xxx
    @RequestMapping("login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        Object errorMsg = request.getAttribute("shiroLoginFailure"); // 异常的名字
        log.info("用户登录错误:[{}]", errorMsg);
        // 如果是账号异常或者是密码异常, 返回用户名或者密码错误
        if (errorMsg != null && IncorrectCredentialsException.class.getName().equals(errorMsg) || UnknownAccountException.class.getName().equals(errorMsg)) {
            PageResult result = PageResult.mark("用户名或者密码错误");
            response.setContentType("text/json;charset=utf-8");
            try {
                response.getWriter().write(JSON.toJSONString(result));
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/login.html";
    }
}
