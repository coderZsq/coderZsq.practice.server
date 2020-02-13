package com.coderZsq._21_upload;

import org.apache.commons.io.FilenameUtils;

public class FileNameUtilTest {
    public static void main(String[] args) {
        String path = "C:/123/abc/outman.png";
        // 获取文件名称
        System.out.println(FilenameUtils.getName(path));
        // 获取文件名称, 但是不包括扩展名
        System.out.println(FilenameUtils.getBaseName(path));
        // 获取拓展名: png
        System.out.println(FilenameUtils.getExtension(path));
    }
}
