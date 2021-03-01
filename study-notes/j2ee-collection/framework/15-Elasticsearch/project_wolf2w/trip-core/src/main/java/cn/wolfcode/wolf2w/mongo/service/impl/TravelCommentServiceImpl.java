package cn.wolfcode.wolf2w.mongo.service.impl;

import cn.wolfcode.wolf2w.mongo.domain.TravelComment;
import cn.wolfcode.wolf2w.mongo.repository.TravelCommentRepository;
import cn.wolfcode.wolf2w.mongo.service.ITravelCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class TravelCommentServiceImpl implements ITravelCommentService {

    @Autowired
    private TravelCommentRepository repository;


    @Override
    public void save(TravelComment travelComment) {
        travelComment.setId(null);
        travelComment.setCreateTime(new Date());
        //处理关联的评论
        String refId = travelComment.getRefComment().getId();
        if(StringUtils.hasLength(refId)){
            //处理关联评论
            TravelComment ref  = repository.findById(refId).orElse(null);
            travelComment.setRefComment(ref);  //评论的评论
            travelComment.setType(TravelComment.TRAVLE_COMMENT_TYPE);
        }else{
            travelComment.setType(TravelComment.TRAVLE_COMMENT_TYPE_COMMENT);
        }
        repository.save(travelComment);
    }

    @Override
    public List<TravelComment> queryByTravelId(String travelId) {
        return repository.findByTravelId(travelId);
    }


}
