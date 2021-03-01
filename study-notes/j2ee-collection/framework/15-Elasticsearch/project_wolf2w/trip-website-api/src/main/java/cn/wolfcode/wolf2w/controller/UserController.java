package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.annotation.RequireLogin;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisVORedisService;
import cn.wolfcode.wolf2w.redis.service.IUserInfoRedisService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import cn.wolfcode.wolf2w.test.Person;
import cn.wolfcode.wolf2w.util.JsonResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "用户资源",description = "用户资源控制器")
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IUserInfoRedisService userInfoRedisService;

    @Autowired
    private IStrategyStatisVORedisService strategyStatisVORedisService;

    @GetMapping("/get")
    public Object get(Long id){
        return userInfoService.getById(id);
    }


    @GetMapping("/checkPhone")
    public Object checkPhone(String phone){

        boolean ret = userInfoService.checkPhone(phone);
        return ret;
    }


    @GetMapping("/sendVerifyCode")
    public Object sendVerifyCode(String phone){
        userInfoService.sendVerifyCode(phone);
        return JsonResult.success();
    }

    @ApiOperation(value = "注册功能",notes = "其实就是新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "手机号码",name = "phone",dataType = "String",required = true),
            @ApiImplicitParam(value = "昵称",name = "nickname",dataType = "String",required = true),
            @ApiImplicitParam(value = "密码",name = "password",dataType = "String",required = true),
            @ApiImplicitParam(value = "确认密码",name = "rpassword",dataType = "String",required = true),
            @ApiImplicitParam(value = "验证",name = "verifyCode",dataType = "String",required = true)
    })
    @PostMapping("/regist")
    public Object regist(String phone, String nickname, String password, String rpassword, String verifyCode){
        userInfoService.regist(phone,nickname, password, rpassword, verifyCode);
        return JsonResult.success();
    }



    @PostMapping("/login")
    public Object login(String username, String password){
        //登录用户对象
        UserInfo user = userInfoService.login(username, password);
        //创建并设置token
        String token = userInfoRedisService.setToken(user);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("token", token);
        return JsonResult.success(map);
    }

    @RequireLogin
    @GetMapping("/currentUser")
    public Object currentUser(HttpServletRequest req, String name){
        String token = req.getHeader("token");
        //拿token去redis换取用户对象
        UserInfo userInfo = userInfoRedisService.getUserByToken(token);
        return JsonResult.success(userInfo);
    }

    @GetMapping("/strategies/favor")
    public Object strFavor(Long sid, Long userId){

        //判断sid攻略是否被uid收藏
        //怎么判断：查询sids集合看是否包含sid
        List<Long> sids = strategyStatisVORedisService.getStrategyIdList(userId);
        return JsonResult.success(sids.contains(sid));
    }

    @ApiResponses({
            @ApiResponse(code=200,message="用户注册成功")
    })
    @GetMapping("/test")
    public Object test(Person person){


        return JsonResult.success();
    }


}
