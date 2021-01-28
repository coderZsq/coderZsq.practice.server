package com.sq.jk.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SysRoleReqVo {
    @ApiModelProperty("id [大于0代表更新, 否则代表添加]")
    private Short id;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称 [不能为空]", required = true)
    private String name;

    @ApiModelProperty("资源id [多个id之间用逗号, 隔开]")
    private String resourceIds;
}