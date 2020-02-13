package com.coderZsq._21_upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FileUtil {
    // 允许接受的图片类型
    private static final String ALLOWED_IMAGE_TYPE = "png;gif;jpg;jpeg;bmp";

    public static void upload(HttpServletRequest req) {
        // 解析和检查请求: 请求方式是否是POST, 请求编码是否是multipart/form-data
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            return;
        }
        try {
            // 1. 创建FileItemFactory
            // FileItemFactory是用来创建FileItem对象的
            // FileItem对象: form表单中的表单控件的封装
            // FileItemFactory factory = new DiskFileItemFactory();
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 2. 创建文件上传处理器
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 设置单个上传文件的大小限制
            upload.setFileSizeMax(1024 * 1024 * 2); // 2M
            // 设置该次请求总数据大小限制
            upload.setSizeMax(1024 * 1024 * 3); // 3M
            // 3. 解析请求
            List<FileItem> items = upload.parseRequest(req);
            // 4. 迭代出每一个FileItem
            for (FileItem item : items) {
                String fieldName = item.getFieldName(); // 获取表单控件的name属性值(参数名)
                if (item.isFormField()) {
                    // 普通表单控件
                    String value = item.getString("UTF-8"); // 获取当前普通表单控件的参数值
                    System.out.println(fieldName + "-" + value);
                } else {
                    // 当前上传文件的MIME类型
                    String mineType = req.getServletContext().getMimeType(item.getName());
                    System.out.println(mineType);
                    // ---------------------------
                    // 上传文件的拓展名
                    String ext = FilenameUtils.getExtension(item.getName());
                    String[] allowedImageType = ALLOWED_IMAGE_TYPE.split(";");

                    // 当前上传文件的类型不在图片允许的格式之内
                    if (!Arrays.asList(allowedImageType).contains(ext)) {
                        throw new LogicException("亲, 请上传正确的图片格式!");
                    }
                    // ---------------------------
                    // 表单上传控件
                    System.out.println("上传文件的名称: " + FilenameUtils.getName(item.getName()));
                    System.out.println(fieldName + "-" + item.getName());
                    String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(item.getName());
                    String dir = req.getServletContext().getRealPath("/upload");
                    item.write(new File(dir, fileName)); // 把二进制数据写到哪一个文件中
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            throw new LogicException("亲, 单个文件大小不能超过2M", e);
        } catch (FileUploadBase.SizeLimitExceededException e){
            throw new LogicException("亲, 该次请求的大小不能超过3M", e);
        } catch (LogicException e) {
            throw e; // 继续抛出异常给调用者(UploadServlet)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
