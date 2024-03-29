package com.sq.imaginist.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.imaginist.common.mapStruct.MapStructs;
import com.sq.imaginist.common.util.JsonVos;
import com.sq.imaginist.common.util.Uploads;
import com.sq.imaginist.pojo.po.Article;
import com.sq.imaginist.pojo.vo.DataJsonVo;
import com.sq.imaginist.pojo.vo.JsonVo;
import com.sq.imaginist.pojo.vo.PageJsonVo;
import com.sq.imaginist.pojo.vo.list.ArticleVo;
import com.sq.imaginist.pojo.vo.req.page.ArticlePageReqVo;
import com.sq.imaginist.pojo.vo.req.save.ArticleReqVo;
import com.sq.imaginist.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
    public JsonVo save(@Valid ArticleReqVo articleReqVo) {
        return JsonVos.ok(service.saveGeneratedId(getFunction().apply(articleReqVo)));
    }

    @PostMapping("/uploadImg")
    @ApiOperation("图片上传")
    public JsonVo uploadImg(MultipartFile img) throws Exception {
        return new DataJsonVo<>(Uploads.uploadImage(img));
    }

    @GetMapping("/edit")
    @ApiOperation("编辑文章")
    public JsonVo edit(ArticlePageReqVo query) {
        return JsonVos.ok(service.getById(query.getId()));
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