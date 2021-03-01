package cn.wolfcode.wolf2w.mongo.repository;

import cn.wolfcode.wolf2w.mongo.domain.StrategyComment;
import cn.wolfcode.wolf2w.mongo.domain.TravelComment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 游记评论持久层接口
 */
public interface TravelCommentRepository extends MongoRepository<TravelComment, String> {

    /**
     * 查询指定游记id的评论列表
     * @param travelId
     * @return
     */
    List<TravelComment> findByTravelId(String travelId);
}
