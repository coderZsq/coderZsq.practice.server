package com.sq.recorder.pojo.vo.req.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class ArticlePageReqVo extends PageReqVo {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("类型")
    private String type;
}
