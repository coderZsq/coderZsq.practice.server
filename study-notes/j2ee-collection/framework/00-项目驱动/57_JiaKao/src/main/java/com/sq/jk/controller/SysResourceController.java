package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.Constants;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.common.util.Streams;
import com.sq.jk.pojo.po.SysResource;
import com.sq.jk.pojo.vo.DataJsonVo;
import com.sq.jk.pojo.vo.JsonVo;
import com.sq.jk.pojo.vo.PageJsonVo;
import com.sq.jk.pojo.vo.list.SysResourceTreeVo;
import com.sq.jk.pojo.vo.list.SysResourceVo;
import com.sq.jk.pojo.vo.req.page.SysResourcePageReqVo;
import com.sq.jk.pojo.vo.req.save.SysResourceReqVo;
import com.sq.jk.service.SysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/sysResources")
@Api(tags = "资源")
public class SysResourceController extends BaseController<SysResource, SysResourceReqVo> {
    @Autowired
    private SysResourceService service;

    @GetMapping("/ids")
    @ApiOperation("根据用户id获取角色id")
    public DataJsonVo<List<Short>> ids(Integer roleId) {
        return JsonVos.ok(service.listIds(roleId));
    }

    @GetMapping
    @ApiOperation(value = "分页查询")
    @RequiresPermissions(Constants.Permission.SYS_RESOURCE_LIST)
    public PageJsonVo<SysResourceVo> list(SysResourcePageReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }

    @GetMapping("/list")
    @ApiOperation("查询所有")
    @RequiresPermissions(Constants.Permission.SYS_RESOURCE_LIST)
    public DataJsonVo<List<SysResourceVo>> list() {
        return JsonVos.ok(Streams.map(service.list(), MapStructs.INSTANCE::po2vo));
    }

    @GetMapping("/listTree")
    @ApiOperation("查询所有 (树状结构展示)")
    @RequiresPermissions(Constants.Permission.SYS_RESOURCE_LIST)
    public DataJsonVo<List<SysResourceTreeVo>> listTree() {
        return JsonVos.ok(service.listTree());
    }

    @GetMapping("/listParents")
    @ApiOperation("查询所有的父资源 (目录, 菜单)")
    @RequiresPermissions(Constants.Permission.SYS_RESOURCE_LIST)
    public DataJsonVo<List<SysResourceVo>> listParents() {
        return JsonVos.ok(service.listParents());
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permission.SYS_RESOURCE_ADD,
            Constants.Permission.SYS_RESOURCE_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(SysResourceReqVo sysResourceReqVo) {
        return super.save(sysResourceReqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permission.SYS_RESOURCE_REMOVE)
    public JsonVo remove(String id) {
        return super.remove(id);
    }

    @Override
    protected IService<SysResource> getService() {
        return service;
    }

    @Override
    protected Function<SysResourceReqVo, SysResource> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}