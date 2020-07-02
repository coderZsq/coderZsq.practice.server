package com.sq.resume.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

public class Uploads {
    private static final String BASE_DIR = "upload";
    private static final String IMG_DIR = "img";

    /**
     * 图片上传
     *
     * @param item     文件参数
     * @param request  请求
     * @param oldImage 以前的图片路径
     * @return 存储到数据库的图片路径
     * @throws Exception
     */
    public static String uploadImage(FileItem item, HttpServletRequest request, String oldImage) throws Exception {
        // 如果oldImage是空字符串, 就设置为null
        if (oldImage != null && oldImage.length() == 0) {
            oldImage = null;
        }

        if (item == null) return oldImage;
        InputStream is = item.getInputStream();
        if (is.available() == 0) return oldImage;

        ServletContext ctx = request.getServletContext();

        String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(item.getName());
        String image = BASE_DIR + "/" + IMG_DIR + "/" + filename;
        String filePath = ctx.getRealPath(image);
        FileUtils.copyInputStreamToFile(item.getInputStream(), new File(filePath));

        // 删除旧的文件
        if (oldImage != null) {
            // 如果oldImage是空串, 那么就会把整个web项目的文件夹给删掉
            FileUtils.deleteQuietly(new File(ctx.getRealPath(oldImage)));
        }

        return image;
    }
}
