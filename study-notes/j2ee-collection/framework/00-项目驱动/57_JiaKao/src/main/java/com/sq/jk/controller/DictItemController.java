package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.exception.CommonException;
import com.sq.jk.common.util.Rs;
import com.sq.jk.pojo.po.DictItem;
import com.sq.jk.pojo.query.DictItemQuery;
import com.sq.jk.pojo.result.R;
import com.sq.jk.pojo.result.SwaggerDictItemR;
import com.sq.jk.pojo.result.SwaggerErrorR;
import com.sq.jk.pojo.result.SwaggerR;
import com.sq.jk.service.DictItemService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/dictItems")
@Api(tags = "数据字典条目")
public class DictItemController extends BaseController<DictItem> {
    @Autowired
    private DictItemService service;

    @GetMapping("/get")
    @ApiOperation("根据id查询对应的数据字典条目")
    public DictItem get(@ApiParam(required = true, value = "id") @RequestParam Integer id) {
        return service.getById(id);
    }

    @GetMapping("/abc")
    @ApiOperation("测试")
    public SwaggerDictItemR abc() {
        return null;
    }

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "可以传入页码、每页大小等参数")
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = SwaggerDictItemR.class)
    })
    public R list(DictItemQuery query) {
        service.list(query);
        return Rs.ok(query);
    }

    @Override
    protected IService<DictItem> getService() {
        return service;
    }
}