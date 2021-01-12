package com.sq.jk.pojo.po;

import com.sq.jk.common.foreign.anno.ForeignField;
import com.sq.jk.common.validator.BoolNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("数据字典条目")
public class DictItem {
    //主键
    @ApiModelProperty("id")
    private Integer id;
    //名称
    @NotBlank(message = "名称不能为空")
    @ApiModelProperty(value = "名称", required = true)
    private String name;
    //值
    @NotBlank(message = "值不能为空")
    @ApiModelProperty(value = "值", required = true)
    private String value;
    //排列顺序, 默认0. 值越大, 就排在越前面
    @Min(value = 0, message = "序号不能是负数")
    @ApiModelProperty("排列顺序, 默认0. 值越大, 就排在越前面")
    private Integer sn;
    //是否禁用. 0代表不禁用(启用), 1代表禁用
    @BoolNumber(message = "disabled只能是0和1")
    @ApiModelProperty("是否禁用. 0代表不禁用(启用), 1代表禁用")
    private Short disabled;
    //所属的类型
    @ForeignField(DictType.class)
    @NotNull
    @ApiModelProperty(value = "数据字典类型的id", required = true)
    private Integer typeId;
}