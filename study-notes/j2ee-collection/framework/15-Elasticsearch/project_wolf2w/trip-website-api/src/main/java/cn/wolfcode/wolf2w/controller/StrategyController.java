package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.annotation.RequireLogin;
import cn.wolfcode.wolf2w.annotation.UserParam;
import cn.wolfcode.wolf2w.domain.*;
import cn.wolfcode.wolf2w.mongo.domain.StrategyComment;
import cn.wolfcode.wolf2w.mongo.query.StrategyCommentQuery;
import cn.wolfcode.wolf2w.mongo.service.IStrategyCommentService;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisVORedisService;
import cn.wolfcode.wolf2w.redis.service.IUserInfoRedisService;
import cn.wolfcode.wolf2w.service.*;
import cn.wolfcode.wolf2w.util.JsonResult;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("strategies")
public class StrategyController {

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IStrategyThemeService strategyThemeService;

    @Autowired
    private IStrategyRankService strategyRankService;

    @Autowired
    private IStrategyCommentService strategyCommentService;

    @Autowired
    private IStrategyConditionService strategyConditionService;

    @Autowired
    private IUserInfoRedisService userInfoRedisService;

    @Autowired
    private IStrategyStatisVORedisService strategyStatisVORedisService;

    @GetMapping("/content")
    public Object content(Long id){
        //List<StrategyCatalog> list
        return JsonResult.success(strategyService.getContent(id));
    }
    @GetMapping("/detail")
    public Object detail(Long id){
        Strategy strategy = strategyService.getById(id);
        StrategyContent content = strategyService.getContent(id);
        strategy.setContent(content);


        //阅读数+1
        strategyStatisVORedisService.viewnumIncrease(id);


        return JsonResult.success(strategy);
    }

    @GetMapping("/themes")
    public Object themes(){
        return JsonResult.success(strategyThemeService.list());
    }

    @GetMapping("/query")
    public Object query(StrategyQuery qo){
        return JsonResult.success(strategyService.queryPage(qo));
    }



    @GetMapping("/rank")
    public Object rank(int type){
        return JsonResult.success(strategyRankService.queryByType(type));
    }



    @GetMapping("/condition")
    public Object condition(int type){
        return JsonResult.success(strategyConditionService.queryByType(type));
    }


    @RequireLogin
    @PostMapping("/commentAdd")
    public Object commentAdd(StrategyComment comment, HttpServletRequest request){
        String token = request.getHeader("token");
        UserInfo user = userInfoRedisService.getUserByToken(token);

        BeanUtils.copyProperties(user, comment);
        comment.setUserId(user.getId());
        strategyCommentService.save(comment);

        //评论数+1
        strategyStatisVORedisService.replynumIncrease(Long.valueOf(comment.getStrategyId()));



        return JsonResult.success();
    }



    @GetMapping("/comments")
    public Object comments(StrategyCommentQuery qo){

        //Spring-data-mongodb
        return JsonResult.success(strategyCommentService.queryPage(qo));
    }

    @GetMapping("/statisVo")
    public Object statisVo(Long sid){

        //Spring-data-mongodb
        return JsonResult.success(strategyStatisVORedisService.getStrategyStatisVO(sid));
    }

    @RequireLogin
    @PostMapping("/favor")
    public Object favor(Long sid,@UserParam UserInfo userInfo){

        boolean ret = strategyStatisVORedisService.favor(sid,userInfo.getId());
        return JsonResult.success(ret);
    }
    @RequireLogin
    @PostMapping("/strategyThumbup")
    public Object strategyThumbup(Long sid,@UserParam UserInfo userInfo){

        boolean ret = strategyStatisVORedisService.strategyThumbup(sid,userInfo.getId());
        return JsonResult.success(ret);
    }
}
