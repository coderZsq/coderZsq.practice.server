package com.coderZsq._31_message.web.request;

import com.coderZsq._31_message.util.FilterUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

// 敏感字过滤请求包装类
public class MessageRequestWrapper extends HttpServletRequestWrapper {
    public MessageRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 覆盖getParameter方法, 使之支持敏感字过滤
     */
    @Override
    public String getParameter(String name) {
        // 如果参数名为title或content
        if ("title".equals(name) || "content".equals(name)) {
            // 返回过滤之后的title和content
            return FilterUtil.filter(super.getParameter(name));
        }
        return super.getParameter(name);
    }
}
