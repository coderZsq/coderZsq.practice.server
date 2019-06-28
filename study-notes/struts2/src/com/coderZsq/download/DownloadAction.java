package com.coderZsq.download;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

// 文件下载
public class DownloadAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) throws Exception {
        this.fileName = fileName;
    }

    // 返回被下载文件的输入流, 把下载文件读取到程序中来
    public InputStream getInputStream() throws Exception {
        String dir = ServletActionContext.getServletContext().getRealPath("/WEB-INF/download");
        File f = new File(dir, fileName);
        return new FileInputStream(f);
    }

    @Override
    public String execute() throws Exception {
        System.out.println("下载文件 = " + fileName);
        return SUCCESS;
    }
}
