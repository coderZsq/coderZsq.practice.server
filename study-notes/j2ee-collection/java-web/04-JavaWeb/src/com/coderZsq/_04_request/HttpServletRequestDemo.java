package com.coderZsq._04_request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

// 演示HttpServletRequest的方法
@WebServlet("/req")
public class HttpServletRequestDemo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 01.String getMethod(); 返回请求方式: 如GET/POST
        System.out.println(req.getMethod());
        // 02.String getRequestURI(); 返回请求行中的资源名字部分; 如 /test/index.html
        System.out.println(req.getRequestURI());
        // 03.StringBuffer getRequestURL(); 返回浏览器地址栏中所有的信息
        System.out.println(req.getRequestURL());
        // 04.String getContextPath(); 返回当前项目的上下文路径(<Context/>元素的path属性值.)
        System.out.println(req.getContextPath());
        // 05.String getRemoteAddr(); 返回发出请求的客户机的IP地址
        System.out.println(req.getRemoteAddr());
        // 06.String getHeader(String name); 返回指定名称的请求头的值
        String userAgent = req.getHeader("User-agent");
        System.out.println(userAgent.contains("MSIE"));
        System.out.println(userAgent);
        System.out.println("----------------------------------");
        // 01.String getParameter(String name); 返回指定参数名字的值
        String username = req.getParameter("username");
        System.out.println(username);
        // 02.String[] getParameterValues(String name); 返回指定名字参数的多个参数值
        String[] hobbys = req.getParameterValues("hobby");
        System.out.println(Arrays.toString(hobbys));
        // 03.Enumeration<String>getParameterNames(); 返回所有参数名的Enumeration对象
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            System.out.println(names.nextElement());
        }
        // 04.Map<String, String[]> getParameterMap(); 返回所有的参数和值所组成的Map对象
        Map<String, String[]> paramMap = req.getParameterMap();
        System.out.println(paramMap);
    }
}
