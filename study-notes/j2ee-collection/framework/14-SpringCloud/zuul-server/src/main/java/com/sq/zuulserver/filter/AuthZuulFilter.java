package com.sq.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

// @Component
public class AuthZuulFilter extends ZuulFilter {
    // 过滤器类型 pre route post error
    @Override
    public String filterType() {
        return "pre";
    }
    // 顺序, 值越小, 顺序优先
    @Override
    public int filterOrder() {
        return 3;
    }
    // 是否需要进行过滤, true: 执行过滤业务 run 方法
    @Override
    public boolean shouldFilter() {
        return true;
    }
    // 具体的业务处理方法
    @Override
    public Object run() throws ZuulException {
        // 业务判断
        // 1 获取到请求对象Request对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        // 2 判断请求参数
        String cookie = request.getHeader("Cookie");
        if (StringUtils.isEmpty(cookie)) {
            cookie = request.getParameter("Cookie");
        }
        // 3 判断cookie是否为空
        if (StringUtils.isEmpty(cookie)) {
            context.setSendZuulResponse(false); // 阻止往下面继续执行
            context.setResponseStatusCode(401); // 认证失败
        }
        return null;
    }
}
