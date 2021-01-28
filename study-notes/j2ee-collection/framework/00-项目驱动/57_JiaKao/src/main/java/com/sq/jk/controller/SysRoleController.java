package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.common.util.Streams;
import com.sq.jk.pojo.po.SysRole;
import com.sq.jk.pojo.result.CodeMsg;
import com.sq.jk.pojo.vo.DataJsonVo;
import com.sq.jk.pojo.vo.JsonVo;
import com.sq.jk.pojo.vo.PageJsonVo;
import com.sq.jk.pojo.vo.list.SysRoleVo;
import com.sq.jk.pojo.vo.list.SysUserVo;
import com.sq.jk.pojo.vo.req.page.SysRolePageReqVo;
import com.sq.jk.pojo.vo.req.page.SysUserPageReqVo;
import com.sq.jk.pojo.vo.req.save.SysRoleReqVo;
import com.sq.jk.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/sysRoles")
@Api(tags = "角色")
public class SysRoleController extends BaseController<SysRole, SysRoleReqVo> {
    @Autowired
    private SysRoleService service;

    @GetMapping("/ids")
    @ApiOperation("根据用户id获取角色id")
    public DataJsonVo<List<Short>> ids(Integer userId) {
        return JsonVos.ok(service.listIds(userId));
    }

    @GetMapping("/list")
    @ApiOperation("查询所有")
    public DataJsonVo<List<SysRoleVo>> list() {
        return JsonVos.ok(Streams.map(service.list(), MapStructs.INSTANCE::po2vo));
    }

    @GetMapping
    @ApiOperation(value = "分页查询")
    @RequiresPermissions("sysRole:list")
    public PageJsonVo<SysRoleVo> list(SysRolePageReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }

    @Override
    public JsonVo save(SysRoleReqVo reqVo) {
        if (service.saveOrUpdate(reqVo)) {
            return JsonVos.ok(CodeMsg.SAVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }
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