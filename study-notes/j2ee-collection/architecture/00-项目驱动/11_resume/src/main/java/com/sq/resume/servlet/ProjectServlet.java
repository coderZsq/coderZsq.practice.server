package com.sq.resume.servlet;

import com.sq.resume.bean.Company;
import com.sq.resume.bean.Project;
import com.sq.resume.bean.UploadParams;
import com.sq.resume.service.CompanyService;
import com.sq.resume.service.impl.CompanyServiceImpl;
import com.sq.resume.util.Uploads;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/project/*")
public class ProjectServlet extends BaseServlet<Project> {
    private CompanyService companyService = new CompanyServiceImpl();

    public void front(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("projects", service.list());
        forward(request, response, "front/project.jsp");
    }

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("projects", service.list());
        request.setAttribute("companies", companyService.list());
        forward(request, response, "admin/project.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UploadParams uploadParams = Uploads.parseRequest(request);

        Project project = new Project();
        BeanUtils.populate(project, uploadParams.getParams());

        // 对公司信息做特殊处理
        Company company = new Company();
        company.setId(Integer.valueOf(uploadParams.getParam("companyId").toString()));
        project.setCompany(company);

        // 项目图片
        FileItem item = uploadParams.getFileParam("imageFile");
        project.setImage(Uploads.uploadImage(item, request, project.getImage()));

        if (service.save(project)) { // 保存成功
            redirect(request, response, "project/admin");
        } else {
            forwardError(request, response, "项目经验保存失败");
        }
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] idStrs = request.getParameterValues("id");
        List<Integer> ids = new ArrayList<>();
        for (String idStr : idStrs) {
            ids.add(Integer.valueOf(idStr));
        }
        // 删除
        if (service.remove(ids)) {
            redirect(request, response, "project/admin");
        } else {
            forwardError(request, response, "项目经验删除失败");
        }
    }
}
