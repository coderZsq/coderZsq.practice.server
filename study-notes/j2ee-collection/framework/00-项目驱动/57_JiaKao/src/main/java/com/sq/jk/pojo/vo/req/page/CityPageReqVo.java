package com.sq.jk.pojo.vo.req.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CityPageReqVo extends KeywordPageReqVo {
    @ApiModelProperty("省份id")
    private Integer parentId;
}
