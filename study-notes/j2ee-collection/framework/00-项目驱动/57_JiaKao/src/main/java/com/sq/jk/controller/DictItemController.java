package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.Constants;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.pojo.po.DictItem;
import com.sq.jk.pojo.vo.JsonVo;
import com.sq.jk.pojo.vo.PageJsonVo;
import com.sq.jk.pojo.vo.list.DictItemVo;
import com.sq.jk.pojo.vo.req.page.DictItemPageReqVo;
import com.sq.jk.pojo.vo.req.save.DictItemReqVo;
import com.sq.jk.service.DictItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.function.Function;

@RestController
@RequestMapping("/dictItems")
@Api(tags = "数据字典条目")
public class DictItemController extends BaseController<DictItem, DictItemReqVo> {
    @Autowired
    private DictItemService service;

    @GetMapping
    @ApiOperation(value = "分页查询")
    @RequiresPermissions(Constants.Permission.DICT_ITEM_LIST)
    public PageJsonVo<DictItemVo> list(DictItemPageReqVo query) {
        return JsonVos.ok(service.list(query));
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permission.DICT_ITEM_ADD,
            Constants.Permission.DICT_ITEM_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(DictItemReqVo reqVo) {
        return super.save(reqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permission.DICT_ITEM_REMOVE)
    public JsonVo remove(String id) {
        return super.remove(id);
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