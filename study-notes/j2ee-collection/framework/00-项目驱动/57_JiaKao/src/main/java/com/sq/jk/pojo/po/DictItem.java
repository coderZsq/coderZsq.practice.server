package com.sq.jk.pojo.po;

import com.sq.jk.common.foreign.anno.ForeignField;
import com.sq.jk.common.validator.BoolNumber;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class DictItem {
    //主键
    private Integer id;
    //名称
    @NotBlank(message = "名称不能为空")
    private String name;
    //值
    @NotBlank(message = "值不能为空")
    private String value;
    //排列顺序, 默认0. 值越大, 就排在越前面
    @Min(value = 0, message = "序号不能是负数")
    private Integer sn;
    //是否禁用. 0代表不禁用(启用), 1代表禁用
    // @Range(min = 0, max = 1, message = "disabled只能是0和1")
    @BoolNumber(message = "disabled只能是0和1")
    private Short disabled;
    //所属的类型
    @ForeignField(DictType.class)
    private Integer typeId;
}