package com.sq.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户")
public class User {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty(hidden = true)
    // 逻辑删除的局部配置
    // @TableLogic(value = "0", delval = "1")
    // 不要查询这个字段
    @TableField(select = false)
    private Short deleted;
}