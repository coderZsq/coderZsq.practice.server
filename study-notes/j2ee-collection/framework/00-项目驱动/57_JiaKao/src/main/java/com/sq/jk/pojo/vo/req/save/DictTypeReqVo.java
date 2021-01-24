package com.sq.jk.pojo.vo.req.save;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DictTypeReqVo {
    @ApiModelProperty("id [大于0代表更新, 否则代表添加]")
    private Integer id;

    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称 [不能为空]", required = true)
    private String name;

    @NotBlank(message = "值不能为空")
    @ApiModelProperty(value = "值 [不能为空]", required = true)
    private String value;

    @ApiModelProperty("简介")
    private String intro;
}