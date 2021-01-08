package com.sq.jk.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DictItemQuery extends KeywordQuery {
    @ApiModelProperty("类型的id")
    private Integer typeId;
}
