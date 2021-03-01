package cn.wolfcode.wolf2w.mongo.service;


import cn.wolfcode.wolf2w.mongo.domain.StrategyComment;
import cn.wolfcode.wolf2w.mongo.query.StrategyCommentQuery;
import org.springframework.data.domain.Page;

/**
 * 攻略评论服务层接口
 */
public interface IStrategyCommentService  {

    /**
     * 添加
     * @param strategyComment
     */
    void save(StrategyComment strategyComment);


    /**
     * 攻略评论分页：spring-data里面的page对象
     * 跟之前mybatis-plus里面ipage /page对象有区分
     * @param qo
     * @return
     */
    Page<StrategyComment> queryPage(StrategyCommentQuery qo);
}
