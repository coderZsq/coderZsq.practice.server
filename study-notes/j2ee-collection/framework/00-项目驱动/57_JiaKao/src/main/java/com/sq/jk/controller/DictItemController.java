package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.pojo.po.DictItem;
import com.sq.jk.pojo.vo.PageJsonVo;
import com.sq.jk.pojo.vo.list.DictItemVo;
import com.sq.jk.pojo.vo.req.page.DictItemPageReqVo;
import com.sq.jk.pojo.vo.req.save.DictItemReqVo;
import com.sq.jk.service.DictItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/dictItems")
@Api(tags = "数据字典条目")
public class DictItemController extends BaseController<DictItem, DictItemReqVo> {
    @Autowired
    private DictItemService service;

    @GetMapping
    @ApiOperation(value = "分页查询")
    public PageJsonVo<DictItemVo> list(DictItemPageReqVo query) {
        return JsonVos.ok(service.list(query));
    }

    @Override
    protected IService<DictItem> getService() {
        return service;
    }

    @Override
    protected Function<DictItemReqVo, DictItem> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}