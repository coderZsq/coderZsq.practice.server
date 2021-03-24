package com.sq.recorder.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.recorder.common.enhance.MpLambdaQueryWrapper;
import com.sq.recorder.common.enhance.MpPage;
import com.sq.recorder.common.mapStruct.MapStructs;
import com.sq.recorder.mapper.ArticleMapper;
import com.sq.recorder.pojo.po.Article;
import com.sq.recorder.pojo.vo.PageVo;
import com.sq.recorder.pojo.vo.list.ArticleVo;
import com.sq.recorder.pojo.vo.req.page.ArticlePageReqVo;
import com.sq.recorder.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    @Transactional(readOnly = true)
    public PageVo<ArticleVo> list(ArticlePageReqVo query) {
        MpLambdaQueryWrapper<Article> wrapper = new MpLambdaQueryWrapper<>();
        Integer id = query.getId();
        if (id != null && id > 0) {
            wrapper.eq(Article::getId, id);
        }
        String type = query.getType();
        if (type != null) {
            wrapper.eq(Article::getType, type);
        }
        wrapper.orderByDesc(Article::getDate);
        return baseMapper
                .selectPage(new MpPage<>(query), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }
}