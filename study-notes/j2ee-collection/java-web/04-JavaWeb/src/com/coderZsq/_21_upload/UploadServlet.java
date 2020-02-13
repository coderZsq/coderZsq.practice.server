package com.coderZsq._21_upload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FileUtil.upload(req);
        } catch (LogicException e) {
            String errorMsg = e.getMessage(); // 获取异常信息
            req.setAttribute("errorMsg", errorMsg);
            req.getRequestDispatcher("/input.jsp").forward(req, resp);
        }
    }
}
