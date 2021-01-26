package com.sq.jk.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("系统角色")
public class SysRoleVo {
    @ApiModelProperty("id")
    private Short id;

    @ApiModelProperty("名称")
    private String name;
}