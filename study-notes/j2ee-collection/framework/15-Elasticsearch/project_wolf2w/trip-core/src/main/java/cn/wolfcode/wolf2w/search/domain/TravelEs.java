package cn.wolfcode.wolf2w.search.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 游记搜索对象
 */
@Getter
@Setter
@Document(indexName="wolf2w_travel",type="travel")
public class TravelEs implements Serializable {

    public static final String INDEX_NAME = "wolf2w_travel";
    public static final String TYPE_NAME = "travel";

    //@Field 每个文档的字段配置（类型、是否分词、是否存储、分词器 ）
    @Id
    @Field(store=true, index = false,type = FieldType.Keyword)
    private String id;  //游记id
    @Field(store=true, index = false,type = FieldType.Keyword)
    private String destId;  //游记地点
    @Field(index=true,store=true,type = FieldType.Keyword)
    private String destName; //游记地点名称
    @Field(index=true,analyzer="ik_max_word",store=true,searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String title;  //游记标题
    @Field(index=true,analyzer="ik_max_word",store=true,searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String summary; //游记简介
}