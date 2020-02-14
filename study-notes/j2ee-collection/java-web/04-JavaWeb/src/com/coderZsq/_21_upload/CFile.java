package com.coderZsq._21_upload;

import lombok.Data;

@Data
public class CFile {
    private String imageUrl; // 图片保存的路径: /upload/123.png JSP: <img src="${user.imageUrl}>
    private String imageName; // 图片原始名称
}
