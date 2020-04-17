package com.coderzsq._08_upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
public class UploadController {
    @Value("${fileupload}")
    private String dir;

    @RequestMapping("upload")
    public String upload(MultipartFile file) throws Exception {
        System.out.println(file.getOriginalFilename());
        // 把文件存储到本地目录
        // 1 获取文件名后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 2 生成一个随机的完整名字
        String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
        // 3 生成一个文件路径
        File dest = new File(dir, fileName);
        // 4 开始保存操作
        file.transferTo(dest);
        return "success";
    }
}
