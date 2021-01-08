package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.util.Rs;
import com.sq.jk.pojo.result.CodeMsg;
import com.sq.jk.pojo.result.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;

@Validated
public abstract class BaseController<T> {
    protected abstract IService<T> getService();

    @PostMapping("/remove")
    @ApiOperation("删除一条或多条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "一个或多个id, 多个id用逗号拼接", required = true)
    })
    public R remove(@NotBlank(message = "id不能为空") @RequestParam String id) {
        if (getService().removeByIds(Arrays.asList(id.split(",")))) {
            return Rs.ok(CodeMsg.REMOVE_SUCCESS);
        } else {
            return Rs.raise(CodeMsg.REMOVE_ERROR);
        }
    }

    @PostMapping("/save")
    @ApiOperation("添加或更新")
    public R save(@Valid T entity) {
        if (getService().saveOrUpdate(entity)) {
            return Rs.ok(CodeMsg.SAVE_SUCCESS);
        } else {
            return Rs.raise(CodeMsg.SAVE_ERROR);
        }
    }
}
