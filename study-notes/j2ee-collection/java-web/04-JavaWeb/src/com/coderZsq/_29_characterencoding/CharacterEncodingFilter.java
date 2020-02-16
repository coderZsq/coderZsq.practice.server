package com.coderZsq._29_characterencoding;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 字符编码过滤器
public class CharacterEncodingFilter implements Filter {
    private String encoding;
    private Boolean forceEncoding = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
        forceEncoding = Boolean.valueOf(filterConfig.getInitParameter("force"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 类型
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // =========================================
        // 设置编码
        // 1. 应用中没有编码并且我自己设置了编码
        // 2. 应用中已经存在编码了, 但是依然要使用我自己设置的编码: 强制使用
        if (hasLength(encoding) && (req.getCharacterEncoding() == null || forceEncoding)) {
            req.setCharacterEncoding(encoding);
        }
        // =========================================
        // 放行
        filterChain.doFilter(req, resp);
    }

    private boolean hasLength(String str) {
        return str != null && !"".equals(str.trim());
    }

    @Override
    public void destroy() {

    }
}
