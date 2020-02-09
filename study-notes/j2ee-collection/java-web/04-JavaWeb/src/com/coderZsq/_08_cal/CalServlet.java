package com.coderZsq._08_cal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 在线版的简易计算器
@WebServlet("/cal")
public class CalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        // =====================================
        // 2. 接受表单的数据
        String op = req.getParameter("op");
        String sNum1 = req.getParameter("num1");
        String sNum2 = req.getParameter("num2");
        String result = "";
        if (hasLength(sNum1) && hasLength(sNum2)) {
            Integer num1 = Integer.valueOf(sNum1);
            Integer num2 = Integer.valueOf(sNum2);
            if ("+".equals(op)) {
                result = num1 + num2 + "";
            } else if ("-".equals(op)) {
                result = num1 - num2 + "";
            }
        }
        // =====================================
        // 1. 输出一个计算器的界面
        out.print("<form action='/cal' method='post'>");
        out.print("<input type='number' name='num1' value='" + sNum1 + "'>");
        out.print("<select name='op'>");
        out.print("<option>+</option>");
        out.print("<option>-</option>");
        out.print("<option>*</option>");
        out.print("<option>/+</option>");
        out.print("</select>");
        out.print("<input type='number' name='num2' value='" + sNum2 + "'>");
        out.print("<input type='submit' value=' = '>");
        out.print("<input type='text' value='" + result + "' disabled>");
        out.print("</form>");
    }

    private boolean hasLength(String str) {
        return str != null && !"".equals(str.trim());
    }
}
