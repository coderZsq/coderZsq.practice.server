package com.sq.imaginist.common.mapStruct;

import com.sq.imaginist.pojo.po.Article;
import com.sq.imaginist.pojo.vo.list.ArticleVo;
import com.sq.imaginist.pojo.vo.req.save.ArticleReqVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * ReqVo -> Po
 * Po -> Vo
 */
@Mapper(uses = {
        MapStructFormatter.class
})
public interface MapStructs {
    MapStructs INSTANCE = Mappers.getMapper(MapStructs.class);

    ArticleVo po2vo(Article po);
    @Mapping(source = "date",
            target = "date",
            qualifiedBy = MapStructFormatter.Mills2Date.class)
    Article reqVo2po(ArticleReqVo reqVo);
}
