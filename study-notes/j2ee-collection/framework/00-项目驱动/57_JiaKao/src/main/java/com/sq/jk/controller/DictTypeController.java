package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.Constants;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.common.util.Streams;
import com.sq.jk.pojo.po.DictType;
import com.sq.jk.pojo.vo.DataJsonVo;
import com.sq.jk.pojo.vo.JsonVo;
import com.sq.jk.pojo.vo.PageJsonVo;
import com.sq.jk.pojo.vo.list.DictTypeVo;
import com.sq.jk.pojo.vo.req.page.DictTypePageReqVo;
import com.sq.jk.pojo.vo.req.save.DictTypeReqVo;
import com.sq.jk.service.DictTypeService;
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
@RequestMapping("/dictTypes")
@Api(tags = "数据字典类型")
public class DictTypeController extends BaseController<DictType, DictTypeReqVo> {
    @Autowired
    private DictTypeService service;

    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(Constants.Permission.DICT_TYPE_LIST)
    public PageJsonVo<DictTypeVo> list(DictTypePageReqVo query) {
        return JsonVos.ok(service.list(query));
    }

    @GetMapping("/list")
    @ApiOperation("查询所有")
    @RequiresPermissions(Constants.Permission.DICT_TYPE_LIST)
    public DataJsonVo<List<DictTypeVo>> list() {
        return JsonVos.ok(Streams.map(service.list(), MapStructs.INSTANCE::po2vo));
    }

    @Override
    @RequiresPermissions(value =  {
            Constants.Permission.DICT_TYPE_ADD,
            Constants.Permission.DICT_TYPE_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(@Valid DictTypeReqVo dictTypeReqVo) {
        return super.save(dictTypeReqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permission.DICT_TYPE_REMOVE)
    public JsonVo remove(String id) {
        return super.remove(id);
    }

    @Override
    protected IService<DictType> getService() {
        return service;
    }

    @Override
    protected Function<DictTypeReqVo, DictType> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}