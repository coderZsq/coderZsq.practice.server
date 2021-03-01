package cn.wolfcode.wolf2w.mongo.service.impl;

import cn.wolfcode.wolf2w.mongo.domain.StrategyComment;
import cn.wolfcode.wolf2w.mongo.query.StrategyCommentQuery;
import cn.wolfcode.wolf2w.mongo.repository.StrategyCommentRepository;
import cn.wolfcode.wolf2w.mongo.service.IStrategyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
//@Transactional
public class StrategyCommentServiceImpl implements IStrategyCommentService {

    @Autowired
    private StrategyCommentRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;   //springdata转门操作mongod封装的工具类

    @Override
    public void save(StrategyComment strategyComment) {
        strategyComment.setId(null);
        strategyComment.setCreateTime(new Date());
        repository.save(strategyComment);
    }

    @Override
    public Page<StrategyComment> queryPage(StrategyCommentQuery qo) {
        //select * from StrategyComment where strategyid = qo.getStrategyId limit ?, ?
        //mongodb的分页
        //类似之前学得： PageResult
        //PageResult [7个核心：currentPage pageSize prev next totalPage data  totalCount]
        //MQL语句拼接对象
        Query query = new Query();
        //                          where strategyid   =  qo.getStrategyId;
        query.addCriteria(Criteria.where("strategyId").is(qo.getStrategyId()));
        //查询totalCount
        //参数1：SQL语句拼接对象一般用来拼接where条件，
        //参数2：指定查询哪一张表
        // select count(id) from StrategyComment where strategyid = qo.getStrategyId
        long count = mongoTemplate.count(query, StrategyComment.class);
        if(count == 0){
            return Page.empty();
        }
        //查询data
        // select * from StrategyComment where strategyid = qo.getStrategyId
        //  limit ?, ?
        //分页参数封装对象
        //参数1：当前页，从0开始算起，
        //参数2：每页显示条数
        Pageable page = PageRequest.of(qo.getCurrentPage()-1, qo.getPageSize());
        query.with(page);
        List<StrategyComment> data = mongoTemplate.find(query, StrategyComment.class);
        //组装
        return new PageImpl<>(data, page, count);
    }
}
