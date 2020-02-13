package com.coderZsq._21_upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = new User();
            // -----------------------------
            // 封装普通表单的数据
            // key: 参数名称
            // value: 参数值
            Map<String, String> fieldMap = new HashMap<>();
            FileUtil.upload(req, fieldMap);
            // -----------------------------
            System.out.println(fieldMap);
            // 再从fieldMap中取出数据, 再设置到User对象中
            System.out.println(user);
        } catch (LogicException e) {
            String errorMsg = e.getMessage(); // 获取异常信息
            req.setAttribute("errorMsg", errorMsg);
            req.getRequestDispatcher("/input.jsp").forward(req, resp);
        }
    }
}
