package com.coderZsq._22_download;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

// 下载资源
@WebServlet("/down")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 0: 权限检查/积分检查
        // 1: 获取被下载的资源文件名称
        String fileNmme = req.getParameter("fileName");
        // fileNmme = new String(fileNmme.getBytes("ISO-8859-1"), "UTF-8");
        // 2: 从服务器中找到被下载资源的绝对路径
        String realPath = super.getServletContext().getRealPath("/WEB-INF/download/" + fileNmme);
        System.out.println(realPath);
        // ========================================
        // 1. 告诉浏览器不要直接打开文件, 而是弹出下载框, 保存文件
        resp.setContentType("application/x-msdownload");
        // 2. 设置下载文件的建议保存名称
        String userAgent = req.getHeader("User-Agent");
        if (userAgent.contains("MSIE")) {
            // IE
            fileNmme = URLEncoder.encode(fileNmme, "UTF-8");
        } else {
            // 非IE
            fileNmme = new String(fileNmme.getBytes("UTF-8"), "ISO-8859-1");
        }
        resp.setHeader("Content-Disposition", "attachment; filename=" + fileNmme);
        // ========================================
        // 3: 磁盘中的文件--->程序中--->浏览器(拷贝操
        // 作)
        Files.copy(Paths.get(realPath), resp.getOutputStream());
    }
}
