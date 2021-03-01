package cn.wolfcode.wolf2w.mongo.service;


import cn.wolfcode.wolf2w.mongo.domain.TravelComment;

import java.util.List;

/**
 * 游记评论服务层接口
 */
public interface ITravelCommentService {

    /**
     * 添加
     * @param travelComment
     */
    void save(TravelComment travelComment);


    /**
     * 查询指定游记的评论列表
     * @param travelId
     * @return
     */
    List<TravelComment> queryByTravelId(String travelId);
}
