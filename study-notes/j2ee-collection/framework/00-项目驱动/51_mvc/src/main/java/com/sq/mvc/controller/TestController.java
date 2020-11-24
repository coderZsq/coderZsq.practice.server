package com.sq.mvc.controller;

import com.sq.mvc.prop.ProjectProperties;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.ClassLoaderObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@RestController
public class TestController {
    @Autowired
    private ProjectProperties properties;

    @GetMapping("/test")
    public String test(Date birthday) {
        return "哈哈哈哈 - " + birthday;
    }

    @PostMapping("/upload")
    public String upload(String username, MultipartFile photo, HttpServletRequest request) throws Exception {
        System.out.println(username);
        System.out.println(photo);

        // 获取文件扩展名
        String extension = FilenameUtils.getExtension(photo.getOriginalFilename());

        // 目标文件
        String dir = properties.getUpload().getImageFullpath();
        String filename = UUID.randomUUID() + "." + extension;
        File file = new File(dir + filename);

        // 创建好目标文件所在的父目录
        FileUtils.forceMkdirParent(file);

        // 将文件数据写到目标文件
        photo.transferTo(file);

        // System.out.println(request.getServletContext().getRealPath(""));
        // /private/var/folders/dr/q415vqjx46n40jw2m8htjfgm0000gn/T/tomcat-docbase.17039982073840826461.8080/

        return "上传成功!!!";
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        // 把文件以附件的形式返回

        // 设置响应头
        response.setHeader("Content-Disposition", "attachment; filename=test.txt");

        // 读取文件
        try (InputStream is = new ClassPathResource("static/txt/test.txt").getInputStream()) {
            // 将文件数据利用response写回到客户端
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
