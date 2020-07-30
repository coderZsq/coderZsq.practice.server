package com.sq.ajax;

import com.sq.UploadParams;
import com.sq.Uploads;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class Test1Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        response.getWriter().write("{\"name\":\"" + name + "\"}");

        // try {
        //     UploadParams params = Uploads.parseRequest(request);
        //     System.out.println(params.getParams());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}
