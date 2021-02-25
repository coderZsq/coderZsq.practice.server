package com.sq.esdemo.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Setter@Getter@ToString@NoArgsConstructor@AllArgsConstructor
// 把对象和索引库关联起来
@Document(indexName = "shop_product", type = "shop_product")
public class Product {
    @Id
    private String id;
    @Field(type = FieldType.Keyword)
    private String brand;
    @Field(type = FieldType.Integer)
    private int price;
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String intro;
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String title;
}
