package com.sq.recorder.pojo.vo.list;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("文章")
public class ArticleVo {
    //主键
    private Integer id;
    //标题
    private String title;
    //类型
    private String type;
    //正文
    private String content;
    //字数
    private Integer words;
    //阅读时长
    private Integer duration;
    //日期
    private Date date;
}