package com.sq.resume.servlet;

import com.sq.resume.bean.Company;
import com.sq.resume.bean.UploadParams;
import com.sq.resume.util.Uploads;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/company/*")
public class CompanyServlet extends BaseServlet<Company> {

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("companies", service.list());
        forward(request, response, "admin/company.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UploadParams uploadParams = Uploads.parseRequest(request);

        Company company = new Company();
        BeanUtils.populate(company, uploadParams.getParams());

        FileItem item = uploadParams.getFileParam("logoFile");
        company.setLogo(Uploads.uploadImage(item, request, company.getLogo()));

        if (service.save(company)) { // 保存成功
            // 重定向到admin
            redirect(request, response, "company/admin");
        } else { // 保存失败
            forwardError(request, response, "公司信息保存失败");
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
            redirect(request, response, "company/admin");
        } else {
            forwardError(request, response, "公司信息删除失败");
        }
    }
}
