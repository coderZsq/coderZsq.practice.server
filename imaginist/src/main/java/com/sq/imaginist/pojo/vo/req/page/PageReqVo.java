package com.sq.imaginist.pojo.vo.req.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageReqVo {
    private static final int DEFAULT_SIZE = 10;
    @ApiModelProperty("页码")
    private long page;

    @ApiModelProperty("每页的大小")
    private long size;

    public long getPage() {
        return Math.max(page, 1);
    }

    public long getSize() {
        return size < 1 ? DEFAULT_SIZE : size;
    }
}
