package com.sq.resume.servlet;

import com.sq.resume.service.AwardService;
import com.sq.resume.service.impl.AwardServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/award/*")
public class AwardServlet extends BaseServlet {
    private AwardService service = new AwardServiceImpl();

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("awards", service.list());
        request.getRequestDispatcher("/WEB-INF/page/admin/award.jsp").forward(request, response);
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        upload.setHeaderEncoding("UTF-8");
        // 一个FileItem就代表一个请求参数 (文件参数, 非文件参数)
        List<FileItem> items = upload.parseRequest(request);
        // 遍历请求参数
        for (FileItem item : items) {
            String fieldName = item.getFieldName();
            if (item.isFormField()) { // 非文件参数
                System.out.println(fieldName + "_" + item.getString("UTF-8"));
            } else { // 文件参数
                System.out.println(fieldName + "_" + item.getName());

                // 图片在硬盘上的存放路径
                String filePath = "";
                FileUtils.copyInputStreamToFile(item.getInputStream(), new File(filePath));

                // FileOutputStream fos = new FileOutputStream(new File(filePath));
                //
                // // 读取客户端发送的文件数据
                // InputStream is = item.getInputStream();
                // byte[] buffer = new byte[4096];
                // int len = -1;
                // while ((len = is.read(buffer)) != -1) {
                //     fos.write(buffer, 0, len);
                // }
                //
                // is.close();
                // fos.close();
            }
        }

        // Award award = new Award();
        // BeanUtils.populate(award, request.getParameterMap());
        // if (service.save(award)) { // 保存成功
        //     // 重定向到admin
        //     response.sendRedirect(request.getContextPath() + "/award/admin");
        // } else { // 保存失败
        //     request.setAttribute("error", "获奖成就保存失败");
        //     request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
        // }
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] idStrs = request.getParameterValues("id");
        List<Integer> ids = new ArrayList<>();
        for (String idStr : idStrs) {
            ids.add(Integer.valueOf(idStr));
        }
        // 删除
        if (service.remove(ids)) {
            response.sendRedirect(request.getContextPath() + "/award/admin");
        } else {
            request.setAttribute("error", "获奖成就删除失败");
            request.getRequestDispatcher("/WEB-INF/page/error.jsp").forward(request, response);
        }
    }
}
