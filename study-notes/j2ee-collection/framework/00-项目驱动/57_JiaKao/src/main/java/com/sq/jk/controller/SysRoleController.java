package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.pojo.po.SysRole;
import com.sq.jk.pojo.vo.PageJsonVo;
import com.sq.jk.pojo.vo.list.SysRoleVo;
import com.sq.jk.pojo.vo.list.SysUserVo;
import com.sq.jk.pojo.vo.req.page.SysRolePageReqVo;
import com.sq.jk.pojo.vo.req.page.SysUserPageReqVo;
import com.sq.jk.pojo.vo.req.save.SysRoleReqVo;
import com.sq.jk.service.SysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/sysRoles")
public class SysRoleController extends BaseController<SysRole, SysRoleReqVo> {
    @Autowired
    private SysRoleService service;

    @GetMapping
    @ApiOperation(value = "分页查询")
    public PageJsonVo<SysRoleVo> list(SysRolePageReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }

    @Override
    protected IService<SysRole> getService() {
        return service;
    }

    @Override
    protected Function<SysRoleReqVo, SysRole> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}