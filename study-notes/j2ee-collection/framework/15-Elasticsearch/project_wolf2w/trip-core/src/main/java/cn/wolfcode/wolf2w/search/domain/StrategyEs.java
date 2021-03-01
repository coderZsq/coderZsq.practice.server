package cn.wolfcode.wolf2w.search.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 攻略搜索对象
 */
@Getter
@Setter
@Document(indexName="wolf2w_strategy",type="strategy")
public class StrategyEs implements Serializable {

    public static final String INDEX_NAME = "wolf2w_strategy";
    public static final String TYPE_NAME = "strategy";
    //@Field 每个文档的字段配置（类型、是否分词、是否存储、分词器 ）
    @Id
    @Field(store=true, index = false,type = FieldType.Long)
    private Long id;  //攻略id
    @Field(index=true,analyzer="ik_max_word",store=true,searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String title;  //攻略标题
    @Field(index=true,analyzer="ik_max_word",store=true,searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String subTitle;  //攻略标题
    @Field(index=true,analyzer="ik_max_word",store=true,searchAnalyzer="ik_max_word",type = FieldType.Text)
    private String summary; //攻略简介
    @Field(store=true, index = false,type = FieldType.Keyword)
    private String destId;  //游记地点
    @Field(index=true,store=true,type = FieldType.Keyword)
    private String destName; //游记地点名称
}