package com.sq.recorder.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.recorder.common.mapStruct.MapStructs;
import com.sq.recorder.common.util.JsonVos;
import com.sq.recorder.pojo.po.Article;
import com.sq.recorder.pojo.vo.PageJsonVo;
import com.sq.recorder.pojo.vo.list.ArticleVo;
import com.sq.recorder.pojo.vo.req.page.ArticlePageReqVo;
import com.sq.recorder.pojo.vo.req.save.ArticleReqVo;
import com.sq.recorder.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
@RequestMapping("/articles")
@Api("文章")
public class ArticleController extends BaseController<Article, ArticleReqVo> {
    @Autowired
    private ArticleService service;

    @GetMapping
    @ApiOperation(value = "分页查询")
    public PageJsonVo<ArticleVo> list(ArticlePageReqVo query) {
        return JsonVos.ok(service.list(query));
    }

    @Override
    protected IService<Article> getService() {
        return service;
    }

    @Override
    protected Function<ArticleReqVo, Article> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}