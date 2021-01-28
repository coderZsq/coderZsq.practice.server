package com.sq.jk.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("树状结构的资源")
public class SysResourceTreeVo {
    @ApiModelProperty("id")
    private Short id;

    @ApiModelProperty("名称")
    private String title;

    @ApiModelProperty("默认是否展开")
    private boolean spread = true;

    @ApiModelProperty("子资源")
    private List<SysResourceTreeVo> children;
}
