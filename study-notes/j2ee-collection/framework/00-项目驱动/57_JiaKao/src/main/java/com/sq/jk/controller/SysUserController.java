package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.cache.Caches;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.shiro.TokenFilter;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.pojo.po.SysUser;
import com.sq.jk.pojo.result.CodeMsg;
import com.sq.jk.pojo.vo.DataJsonVo;
import com.sq.jk.pojo.vo.JsonVo;
import com.sq.jk.pojo.vo.LoginVo;
import com.sq.jk.pojo.vo.PageJsonVo;
import com.sq.jk.pojo.vo.list.SysUserVo;
import com.sq.jk.pojo.vo.req.LoginReqVo;
import com.sq.jk.pojo.vo.req.page.SysUserPageReqVo;
import com.sq.jk.pojo.vo.req.save.SysUserReqVo;
import com.sq.jk.service.SysUserService;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Function;

@RestController
@RequestMapping("/sysUsers")
@Api(tags = "用户")
public class SysUserController extends BaseController<SysUser, SysUserReqVo> {
    @Autowired
    private SysUserService service;

    @GetMapping("/captcha")
    @ApiOperation("生成验证码")
    public void captcha(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public DataJsonVo<LoginVo> login(LoginReqVo reqVo, HttpServletRequest request) {
        if (CaptchaUtil.ver(reqVo.getCaptcha(), request)) {
            return JsonVos.ok(service.login(reqVo));
        }
        return JsonVos.raise(CodeMsg.WRONG_CAPTCHA);
    }

    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public JsonVo logout(@RequestHeader(TokenFilter.HEADER_TOKEN) String token) {
        Caches.removeToken(token);
        return JsonVos.ok();
    }

    @PostMapping("/saveUser")
    @ApiOperation("添加或更新 [包含角色信息]")

    public JsonVo saveUser(SysUserReqVo reqVo) {
        if (service.saveOrUpdate(reqVo)) {
            return JsonVos.ok(CodeMsg.SAVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }
    }

    @GetMapping
    @ApiOperation(value = "分页查询")
    @RequiresPermissions("sysUser:list")
    public PageJsonVo<SysUserVo> list(SysUserPageReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }

    @Override
    protected IService<SysUser> getService() {
        return service;
    }

    @Override
    protected Function<SysUserReqVo, SysUser> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}