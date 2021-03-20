package com.sq.recorder.pojo.po;

import lombok.Data;

@Data
public class Article {
    private String title;
    private String date;
    private Integer words;
    private Integer duration;
    private String content;
}
