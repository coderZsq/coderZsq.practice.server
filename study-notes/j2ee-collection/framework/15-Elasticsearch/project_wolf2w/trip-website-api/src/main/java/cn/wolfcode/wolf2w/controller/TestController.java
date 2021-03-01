package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class TestController {

    @GetMapping("/test")
    public Object test1(){
        return JsonResult.success("test.....");
    }

    @GetMapping("/test2")
    public Object test2(String aa, String bb, String cc, String dd, String sign){
        return JsonResult.success(Arrays.asList(aa, bb,cc, dd, sign));
    }
}
