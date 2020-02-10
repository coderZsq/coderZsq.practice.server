package com.coderZsq._16_scope;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 从/scope资源跳转到/result资源, 并共享数据
@WebServlet("/scope")
public class ScopeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // request:
        Integer numInRequest = (Integer)req.getAttribute("NUM_IN_REQUEST");
        if (numInRequest == null) {
            req.setAttribute("NUM_IN_REQUEST", 1);
        } else {
            req.setAttribute("NUM_IN_REQUEST", numInRequest + 1);
        }
        // --------------------------------------
        // session:
        Integer numInSession = (Integer)req.getSession().getAttribute("NUM_IN_SESSION");
        if (numInSession == null) {
            req.getSession().setAttribute("NUM_IN_SESSION", 1);
        } else {
            req.getSession().setAttribute("NUM_IN_SESSION", numInSession + 1);
        }
        // --------------------------------------
        // application
        Integer numInApp = (Integer)req.getServletContext().getAttribute("NUM_IN_APP");
        if (numInApp == null) {
            req.getServletContext().setAttribute("NUM_IN_APP", 1);
        } else {
            req.getServletContext().setAttribute("NUM_IN_APP", numInApp + 1);
        }
        req.getRequestDispatcher("/result").forward(req, resp);
    }
}
