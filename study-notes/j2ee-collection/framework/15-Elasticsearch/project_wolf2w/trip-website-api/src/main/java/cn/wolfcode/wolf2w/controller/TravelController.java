package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.annotation.RequireLogin;
import cn.wolfcode.wolf2w.annotation.UserParam;
import cn.wolfcode.wolf2w.domain.*;
import cn.wolfcode.wolf2w.mongo.domain.TravelComment;
import cn.wolfcode.wolf2w.mongo.service.ITravelCommentService;
import cn.wolfcode.wolf2w.query.StrategyQuery;
import cn.wolfcode.wolf2w.query.TravelQuery;
import cn.wolfcode.wolf2w.redis.service.IUserInfoRedisService;
import cn.wolfcode.wolf2w.service.*;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("travels")
public class TravelController {

    @Autowired
    private ITravelService travelService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private ITravelCommentService travelCommentService;

    @Autowired
    private IUserInfoRedisService userInfoRedisService;


    @GetMapping("/query")
    public Object query(TravelQuery qo){
        return JsonResult.success(travelService.queryPage(qo));
    }
    @GetMapping("/detail")
    public Object detail(Long id){
        Travel travel = travelService.getById(id);
        UserInfo userInfo = userInfoService.getById(travel.getAuthorId());
        travel.setAuthor(userInfo);
        TravelContent content = travelService.getContent(id);

        travel.setContent(content);
        return JsonResult.success(travel);
    }

    @GetMapping("/comments")
    public Object comments(String travelId){
        return JsonResult.success(travelCommentService.queryByTravelId(travelId));
    }

    @RequireLogin
    @PostMapping("/commentAdd")
    public Object commentAdd(TravelComment comment, HttpServletRequest request){
        String token = request.getHeader("token");
        UserInfo user = userInfoRedisService.getUserByToken(token);

        BeanUtils.copyProperties(user, comment);
        comment.setUserId(user.getId());

        travelCommentService.save(comment);
        return JsonResult.success();
    }

    /**
     *
     * 上面评论添加的接口需要获取当前登录用户对象， 需要执行下面逻辑
     *  public Object info( HttpServletRequest request){
     *         String token = request.getHeader("token");
     *         UserInfo user = userInfoRedisService.getUserByToken(token);
     *  }
     * 上述操作，存在一个问题，如果很多接口都需要获取当前登录对象时，上述代码重复了
     * 那么该如何解决呢？
     *
     * 思考：提出设想：能不能让获取当前登录用户对象使用下面操作方式呢？
     *  //要获取当前登录对象只需要在请求映射方法参数列表中声明即可
     *  @RequestMapping("/info")
     *  public Object info(UserInfo userInfo){
     *      //userInfo就是当前登录用户对象
     *      return JsonResult.success(userInfo);
     *  }
     *  现阶段springmvc无法直接实现，
     *  此时需要一个自定义的参数解析器，用于获取当前登录用户对象。
     *
     *  springmvc 通过参数解析器将请求携带参数，解析并注入到请求映射方法参数列表中。
     *
     *
     */
    @RequestMapping("/info")
    public Object info(@UserParam UserInfo userInfo){
        return JsonResult.success(userInfo);
    }

    @RequestMapping("/update")
    public Object update(UserInfo userInfo){
        return JsonResult.success(userInfo);
    }


}
