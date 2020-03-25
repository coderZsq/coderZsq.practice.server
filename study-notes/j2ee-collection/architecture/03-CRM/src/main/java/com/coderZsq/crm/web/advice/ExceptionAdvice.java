package com.coderZsq.crm.web.advice;

import com.alibaba.fastjson.JSON;
import com.coderZsq.crm.common.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    //只针对没有权限的异常处理
    @ExceptionHandler(value = {UnauthorizedException.class})
    public String handlerUnauthorizedException(UnauthorizedException ex, HttpServletRequest request, HttpServletResponse response) {
        //打印异常信息
        log.error(ex.getMessage());
        //判断是异步请求还是同步请求---> X-Requested-With: XMLHttpRequest--> 异步请求
        String headerValue = request.getHeader("X-Requested-With");
        // 如果是异步请求, 返回一个PageResult对象
        if (!StringUtils.isEmpty(headerValue) && "XMLHttpRequest".equals(headerValue)) {
            PageResult result = PageResult.mark(ex.getMessage());
            response.setContentType("text/json;charset=utf-8");
            try {
                //通过response对象输出json数据
                log.error("json响应: {}", JSON.toJSONString(result));
                response.getWriter().write(JSON.toJSONString(result));
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 如果是同步请求, 返回一个视图名称common/nopermission
        log.error("同步响应: {}", JSON.toJSONString(ex));
        return "common/nopermission";
    }

    @ExceptionHandler(value = {Exception.class})
    public String handlerException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("ExceptionAdvice.handlerException");
        log.error(ex.getMessage());
        //打印异常信息
        ex.printStackTrace();
        //判断是异步请求还是同步请求---> X-Requested-With: XMLHttpRequest--> 异步请求
        String headerValue = request.getHeader("X-Requested-With");
        // 如果是异步请求, 返回一个PageResult对象
        if (!StringUtils.isEmpty(headerValue) && "XMLHttpRequest".equals(headerValue)) {
            PageResult result = PageResult.mark(ex.getMessage());
            response.setContentType("text/json;charset=utf-8");
            try {
                //通过response对象输出json数据
                log.error("json响应: {}", JSON.toJSONString(result));
                response.getWriter().write(JSON.toJSONString(result));
                //结束返回
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 如果是同步请求, 返回一个视图名称common/error
        request.setAttribute("ex", ex);
        log.error("同步响应: {}", JSON.toJSONString(ex));
        return "common/error";
    }
}
