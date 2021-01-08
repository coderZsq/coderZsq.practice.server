package com.sq.jk.pojo.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageQuery {
    private static final int DEFAULT_SIZE = 10;
    @ApiModelProperty("页码")
    private long page;
    @ApiModelProperty("每页的大小")
    private long size;
    /**
     * 当前这一页的数据
     */
    @ApiModelProperty(hidden = true)
    private List<?> data;
    /**
     * 总数
     */
    @ApiModelProperty(hidden = true)
    private long count;
    /**
     * 总页数
     */
    @ApiModelProperty(hidden = true)
    private long pages;

    public long getPage() {
        return Math.max(page, 1);
    }

    public long getSize() {
        return size < 1 ? DEFAULT_SIZE : size;
    }
}
