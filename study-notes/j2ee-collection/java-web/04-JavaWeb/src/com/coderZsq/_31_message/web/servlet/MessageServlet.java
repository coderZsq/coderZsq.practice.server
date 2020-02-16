package com.coderZsq._31_message.web.servlet;

import com.coderZsq._31_message.dao.IMessageDAO;
import com.coderZsq._31_message.dao.impl.MessageDAOImpl;
import com.coderZsq._31_message.domain.Message;
import com.coderZsq._31_message.util.CommUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/msg")
public class MessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IMessageDAO dao = new MessageDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("cmd");
        if (CommUtil.hasLength(cmd)) {
            if ("save".equals(cmd)) {
                String title = req.getParameter("title");
                String content = req.getParameter("content");
                // ---------------------------------
                // 处理非法文字过滤功能
                // title = FilterUtil.filter(title);
                // content = FilterUtil.filter(content);
                // ---------------------------------
                Message msg = new Message();
                msg.setSn(UUID.randomUUID().toString());
                msg.setTitle(title);
                msg.setContent(content);
                dao.add(msg);
            }
        }
        List<Message> list = dao.getAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/msgs.jsp").forward(req, resp);
    }
}
