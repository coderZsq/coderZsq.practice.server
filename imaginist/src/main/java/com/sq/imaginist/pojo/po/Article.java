package com.sq.imaginist.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Article {
    //主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    //标题
    private String title;
    //类型
    private String type;
    //正文
    private String content;
    //预览
    private String preview;
    //字数
    private Integer words;
    //阅读时长
    private Integer duration;
    //日期
    private Date date;
}