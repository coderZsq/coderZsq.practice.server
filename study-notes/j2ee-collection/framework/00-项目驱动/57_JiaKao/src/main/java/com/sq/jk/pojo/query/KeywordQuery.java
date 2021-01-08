package com.sq.jk.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class KeywordQuery extends PageQuery {
    @ApiModelProperty("搜索关键词")
    private String keyword;
}
