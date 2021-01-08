package com.sq.jk.pojo.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("错误的返回结果")
@Data
public class SwaggerErrorR {
    @ApiModelProperty("错误代码 (非0)")
    private int code;
    @ApiModelProperty("错误消息")
    private String msg;
}
