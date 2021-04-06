package com.sq.imaginist.common.util;

import com.sq.imaginist.common.prop.ImaginistProperties;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class Uploads {
    private static final ImaginistProperties.Upload UPLOAD;
    static {
        UPLOAD = ImaginistProperties.get().getUpload();
    }

    /**
     * 上传图片
     * @param multipartFile 图片数据
     */
    public static String uploadImage(MultipartFile multipartFile) throws Exception {
        // 目录 (相对)
        String dirPath = UPLOAD.getImageRelativePath();
        // 返回图像对象
        return uploadFile(multipartFile, dirPath);
    }

    /**
     *
     * 上传文件
     * @param multipartFile 文件数据
     * @param dir 文件存放到哪一个相对路径 (比如如果是图片, 相对路径是upload/img/)
     */
    private static String uploadFile(MultipartFile multipartFile, String dir) throws Exception {
        if (multipartFile == null || multipartFile.getSize() == 0) return null;
        // 文件扩展名
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        // 文件名
        String filename = UUID.randomUUID() + "." + extension;
        // 文件路径 (相对, upload.img/xx.jpg)
        String filepath = dir + filename;
        // 文件路径 (绝对, F:/jk/upload/img/xxx.jpg)
        String fullFilepath = UPLOAD.getBasePath() + filepath;
        File file = new File(fullFilepath);
        // 如果文件夹不存在, 那就会创建文件夹
        FileUtils.forceMkdirParent(file);
        // 剪切
        multipartFile.transferTo(file);
        // 返回图片对象
        return filepath;
    }

    public static void deleteFile(String relativeFilepath) throws Exception {
        if (Strings.isEmpty(relativeFilepath)) return;

        // 排除只包含路径分隔符的路径
        // String path = relativeFilepath.replace("/", "").replace("\\", "");
        // if (path.length() == 0) return;

        String fullFilepath = UPLOAD.getBasePath() + relativeFilepath;
        File file = new File(fullFilepath);
        // 如果是目录, 就不删除
        if (file.isDirectory()) return;

        // 强制删除文件
        FileUtils.forceDelete(file);
    }
}
