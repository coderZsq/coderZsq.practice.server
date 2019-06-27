package com.coderZsq.upload;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;

public class UploadAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    private File headImg; // 上传的图片对象
    private String headImgFileName; // 上传文件的名称
    private String headImgContentType; //上传文件的MIME类型

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }

    @Override
    public String execute() throws Exception {
        System.out.println("..." + username);
        System.out.println(headImg);
        System.out.println(headImgFileName);
        System.out.println(headImgContentType);

        // 获取保存上传文件的目录
        String dir = ServletActionContext.getServletContext().getRealPath("/upload");
        // 构建上传文件的目录和名称
        File destFile = new File(dir, headImgFileName);
        // 文件内容拷贝
        FileUtils.copyFile(headImg, destFile);

        return NONE;
    }
}
