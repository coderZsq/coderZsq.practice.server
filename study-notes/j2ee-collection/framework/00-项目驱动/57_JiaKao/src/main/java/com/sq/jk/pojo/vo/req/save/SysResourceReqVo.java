package com.sq.jk.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class SysResourceReqVo {
    @ApiModelProperty("id [大于0代表更新, 否则代表添加]")
    private Short id;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称 [不能为空]", required = true)
    private String name;

    @ApiModelProperty("链接")
    private String uri;

    @ApiModelProperty("权限标识")
    private String permission;

    @Range(min = 0, max = 2, message = "类型只能是0, 1, 2")
    @ApiModelProperty(value = "类型 [0是目录, 1是菜单, 2是按钮]", required = true)
    private Short type;

    @ApiModelProperty("图标")
    private String icon;

    @Min(value = 0, message = "序号不能是负数")
    @ApiModelProperty("序号 [不能是负数, 值越大, 就排在越前面, 默认0]")
    private Short sn;

    @ApiModelProperty("父资源id [如果是目录, 父资源id为0; 如果不是目录, 父资源id大于0; 默认0]")
    private Short parentId;
}