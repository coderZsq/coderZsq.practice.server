package cn.wolfcode.wolf2w.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StrategyQuery extends QueryObject {
    private Long destId;
    private Long themeId;

    private Integer type;  //不同类型

    private Long refid;    //1/2目的地id， 3：主题id

    private String orderBy;  //排序



}
