package com.sq.imaginist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.imaginist.pojo.po.Article;
import com.sq.imaginist.pojo.vo.PageVo;
import com.sq.imaginist.pojo.vo.list.ArticleVo;
import com.sq.imaginist.pojo.vo.req.page.ArticlePageReqVo;

public interface ArticleService extends IService<Article> {

    PageVo<ArticleVo> list(ArticlePageReqVo query);
}