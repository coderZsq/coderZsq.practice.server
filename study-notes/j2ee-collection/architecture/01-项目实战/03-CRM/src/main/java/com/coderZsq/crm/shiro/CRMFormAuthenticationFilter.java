package com.coderZsq.crm.shiro;

import com.alibaba.fastjson.JSON;
import com.coderZsq.crm.common.PageResult;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CRMFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //判断请求是异步请求还是同步请求
        //判断是异步请求还是同步请求---> X-Requested-With: XMLHttpRequest--> 异步请求
        if (request instanceof HttpServletRequest) {
            String headerValue = ((HttpServletRequest) request).getHeader("X-Requested-With");
            // 如果是异步请求, 返回一个PageResult对象
            if (!StringUtils.isEmpty(headerValue) && "XMLHttpRequest".equals(headerValue)) {
                PageResult result = PageResult.success();
                //设置response响应编码
                response.setContentType("text/json;charset=utf-8");
                //输出一个json对象
                response.getWriter().write(JSON.toJSONString(result));
                return false;//过滤器返回false 终止程序运行
            }
        }
        // 如果是同步请求, 直接调用父类的方法即可
        return super.onLoginSuccess(token, subject, request, response);
    }
}
