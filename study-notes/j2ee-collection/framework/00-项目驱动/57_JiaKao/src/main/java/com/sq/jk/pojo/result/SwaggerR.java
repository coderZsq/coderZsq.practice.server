package com.sq.jk.pojo.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("成功的返回结果")
@Data
public class SwaggerR<T> {
    @ApiModelProperty("正确代码 (固定是0)")
    private int code;
    @ApiModelProperty("消息")
    private String msg;
    @ApiModelProperty("具体数据")
    private T data;
}
