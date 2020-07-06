package com.sq.resume.servlet;

import com.sq.resume.bean.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet<User> {

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}
