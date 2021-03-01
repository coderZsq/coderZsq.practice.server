package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {

    @RequestMapping("/uploadImg")
    @ResponseBody
    public Object uploadImg(MultipartFile pic) throws Exception {
        //思考：将上传过来的图片保存在哪里？？？？
        String path = UploadUtil.uploadAli(pic);
        return path;
    }

    @RequestMapping("/uploadImg_ck")
    @ResponseBody
    public Object uploadImg_ck(MultipartFile upload) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        String imagePath= null;
        if(upload != null && upload.getSize() > 0){
            try {
                //图片保存, 返回路径
                imagePath =  UploadUtil.uploadAli(upload);
                //表示保存成功
                map.put("uploaded", 1);
                map.put("url",imagePath);

            }catch (Exception e){
                e.printStackTrace();
                map.put("uploaded", 0);
                Map<String, Object> mm = new HashMap<String, Object>();
                mm.put("message",e.getMessage() );
                map.put("error", mm);
            }
        }
        return map;

    }
}
