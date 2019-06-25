package com.coderZsq;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

// 前端控制器
public class ActionFilter implements javax.servlet.Filter {

    // key: 存储<action>元素的name属性
    // value: 存储<action>元素的封装类: ActionConfig对象
    private Map<String, ActionConfig> actionConfigMap = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Document document = this.getDocument();
        // 获取actions.xml中所有的<action/>元素, 每一个<action/>元素应该封装成一个ActionConfig对象
        NodeList nodeList = document.getElementsByTagName("action");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element actionEl = (Element)nodeList.item(i); // <action>元素
            String name = actionEl.getAttribute("name");
            String className = actionEl.getAttribute("class");
            String method = actionEl.getAttribute("method");
            ActionConfig actionConfig = new ActionConfig(name, className, method);
            actionConfigMap.put(name, actionConfig);
            // 读取<action>元素中的<result>元素, 并封装成对象
            NodeList resultNodeList = actionEl.getElementsByTagName("result");
            for (int j = 0; j < resultNodeList.getLength(); j++) {
                Element resultEl = (Element)resultNodeList.item(j);
                String resultName = resultEl.getAttribute("name");
                String type = resultEl.getAttribute("type");
                String path = resultEl.getTextContent();

                ResultConfig resultConfig = new ResultConfig(resultName, type, path);
                actionConfig.getResultConfigMap().put(resultName, resultConfig);
            }
        }
        System.out.println(actionConfigMap);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
//        System.out.println("通用的操作");

        ActionContext context = new ActionContext(request, response);
        ActionContext.setContext(context);

        // 获取请求的资源名称
        String requesturi = request.getRequestURI().substring(1);
//        System.out.println(requesturi);
//        if ("employee".equals(requesturi)) {
//            EmployeeAction action = new EmployeeAction();
//            action.execute();
//        } else if ("department".equals(requesturi)) {
//            DepartmentAction action = new DepartmentAction();
//            action.execute();
//        }
        ActionConfig actionConfig = actionConfigMap.get(requesturi);
        if (actionConfig == null) {
            filterChain.doFilter(request, response);
            return;
        }
        String className = actionConfig.getClassName();
        String method = actionConfig.getMethod();
        try {
            Class actionClass = Class.forName(className);
            Object actionObject = actionClass.newInstance();
            Method method1 = actionClass.getMethod(method);
            // 使用反射调用方法, 返回逻辑视图名称
            Object ret = method1.invoke(actionObject);
            if (ret != null) {
                String viewName = ret.toString();
                // 根据逻辑视图名, 取出对于的ResultConfig对象
                ResultConfig resultConfig = actionConfig.getResultConfigMap().get(viewName);
                if (resultConfig != null) {
                    // 跳转方式
                    String type = resultConfig.getType();
                    // 物理路径
                    String path = resultConfig.getPath();
                    if ("dispatcher".equals(type)) {
                        request.getRequestDispatcher(path).forward(request, response);
                    } else if ("redirect".equals(type)) {
                        response.sendRedirect(request.getContextPath() + path);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Document getDocument() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("actions.xml");
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void destroy() {

    }
}
