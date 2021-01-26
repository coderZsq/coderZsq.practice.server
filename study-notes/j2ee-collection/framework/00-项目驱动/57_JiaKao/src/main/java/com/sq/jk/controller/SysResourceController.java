package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.pojo.po.SysResource;
import com.sq.jk.pojo.vo.PageJsonVo;
import com.sq.jk.pojo.vo.list.SysResourceVo;
import com.sq.jk.pojo.vo.list.SysUserVo;
import com.sq.jk.pojo.vo.req.page.SysResourcePageReqVo;
import com.sq.jk.pojo.vo.req.page.SysUserPageReqVo;
import com.sq.jk.pojo.vo.req.save.SysResourceReqVo;
import com.sq.jk.service.SysResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/sysResources")
@Api(tags = "资源")
public class SysResourceController extends BaseController<SysResource, SysResourceReqVo> {
    @Autowired
    private SysResourceService service;

    @GetMapping
    @ApiOperation(value = "分页查询")
    public PageJsonVo<SysResourceVo> list(SysResourcePageReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
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