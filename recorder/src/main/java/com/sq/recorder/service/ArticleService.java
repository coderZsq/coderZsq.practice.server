package com.sq.recorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.recorder.pojo.po.Article;
import com.sq.recorder.pojo.vo.PageVo;
import com.sq.recorder.pojo.vo.list.ArticleVo;
import com.sq.recorder.pojo.vo.req.page.ArticlePageReqVo;

public interface ArticleService extends IService<Article> {

    PageVo<ArticleVo> list(ArticlePageReqVo query);
}