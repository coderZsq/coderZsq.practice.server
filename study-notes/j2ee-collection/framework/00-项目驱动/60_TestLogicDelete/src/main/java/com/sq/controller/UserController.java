package com.sq.controller;

import com.sq.po.User;
import com.sq.result.DataJsonVo;
import com.sq.result.JsonVo;
import com.sq.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "用户")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/list")
    @ApiOperation("查询所有")
    public DataJsonVo<List<User>> list() {
        return new DataJsonVo<>(service.list());
    }

    @PostMapping("/save")
    @ApiOperation("添加或更新")
    public JsonVo save(User user) {
        JsonVo jsonVo = new JsonVo();
        jsonVo.setMsg(service.saveOrUpdate(user) ? "保存成功" : "保存失败");
        return jsonVo;
    }

    @PostMapping("/remove")
    @ApiOperation("删除")
    public JsonVo remove(Long id) {
        JsonVo jsonVo = new JsonVo();
        jsonVo.setMsg(service.removeById(id) ? "删除成功" : "删除失败");
        return jsonVo;
    }
}