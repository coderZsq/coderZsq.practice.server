package cn.wolfcode.wolf2w.mongo.repository;

import cn.wolfcode.wolf2w.mongo.domain.StrategyComment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 攻略评论持久层接口
 */
public interface StrategyCommentRepository extends MongoRepository<StrategyComment, String> {
}
