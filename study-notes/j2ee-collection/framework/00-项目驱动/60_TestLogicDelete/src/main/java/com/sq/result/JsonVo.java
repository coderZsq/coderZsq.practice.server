package com.sq.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("返回结果")
public class JsonVo {
    @ApiModelProperty("代码")
    private int code;
    @ApiModelProperty("消息")
    private String msg;

    public JsonVo() {}

    public JsonVo(String msg) {
        this.msg = msg;
    }
}
