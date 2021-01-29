package com.sq.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("返回结果【带数据】")
public class DataJsonVo<T> extends JsonVo {
    @ApiModelProperty("具体数据")
    private T data;

    public DataJsonVo(T data) {
        this.data = data;
    }
}
