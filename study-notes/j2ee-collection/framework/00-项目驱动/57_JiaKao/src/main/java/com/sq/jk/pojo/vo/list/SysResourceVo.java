package com.sq.jk.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("系统资源")
public class SysResourceVo {
    @ApiModelProperty("id")
    private Short id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("链接")
    private String uri;

    @ApiModelProperty("权限标识")
    private String permission;

    @ApiModelProperty("类型 (0是目录, 1是菜单, 2是按钮)")
    private Short type;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("序号")
    private Short sn;

    @ApiModelProperty("父资源id")
    private Short parentId;
}