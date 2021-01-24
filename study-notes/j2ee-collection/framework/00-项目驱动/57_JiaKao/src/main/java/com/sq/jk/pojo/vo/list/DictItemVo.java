package com.sq.jk.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("数据字典条目")
public class DictItemVo {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("值")
    private String value;

    @ApiModelProperty("序号 [值越大, 就排在越前面]")
    private Integer sn;

    @ApiModelProperty("是否禁用 [0代表不禁用(启用), 1代表禁用]")
    private Short disabled;

    @NotNull
    @ApiModelProperty("数据字典类型的id")
    private Integer typeId;
}