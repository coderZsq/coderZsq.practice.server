package com.sq.jk.pojo.vo.req.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DictItemPageReqVo extends KeywordPageReqVo {
    @ApiModelProperty("数据字典类型的id")
    private Integer typeId;
}
