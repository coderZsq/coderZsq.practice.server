package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.search.domain.DestinationEs;
import cn.wolfcode.wolf2w.search.domain.StrategyEs;
import cn.wolfcode.wolf2w.search.domain.TravelEs;
import cn.wolfcode.wolf2w.search.domain.UserInfoEs;
import cn.wolfcode.wolf2w.search.query.SearchQueryObject;
import cn.wolfcode.wolf2w.search.service.ISearchService;
import cn.wolfcode.wolf2w.search.vo.SearchResultVO;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import cn.wolfcode.wolf2w.util.JsonResult;
import cn.wolfcode.wolf2w.util.ParamMap;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@AllArgsConstructor
public class SearchController {

    private IDestinationService destinationService;
    private IUserInfoService userInfoService;
    private ISearchService searchService;


    @RequestMapping("/q")
    public Object search(SearchQueryObject qo) throws UnsupportedEncodingException {
        //1 进行参数转码
        final String kw = URLDecoder.decode(qo.getKeyword(), "UTF-8");
        qo.setKeyword(kw);
        switch (qo.getType()){
            case SearchQueryObject.TYPE_DEST: return this.searchDest(qo);
            case SearchQueryObject.TYPE_STRATEGY: return this.searchStrategy(qo);
            case SearchQueryObject.TYPE_TRAVEL: return this.searchTravel(qo);
            case SearchQueryObject.TYPE_USER: return this.searchUser(qo);
            default: return this.searchAll(qo);
        }
    }

    private Object searchAll(SearchQueryObject qo) {
        Page<UserInfo> us = this.createUserPage(qo);
        Page<Strategy> sts =this.createStrategyPage(qo);
        Page<Travel> ts = this.createTravelPage(qo);
        Page<Destination> dest = this.createDestPage(qo);
        SearchResultVO vo = new SearchResultVO();
        vo.setUsers(us.getContent());
        vo.setStrategys(sts.getContent());
        vo.setTravels(ts.getContent());
        vo.setDests(dest.getContent());
        vo.setTotal(us.getTotalElements() + sts.getTotalElements() + ts.getTotalElements() + dest.getTotalElements());
        return JsonResult.success(ParamMap.newInstance().put("result",vo).put("qo",qo));
    }

    private Object searchUser(SearchQueryObject qo) {
        final Page<UserInfo> userPage = createUserPage(qo);
        return JsonResult.success(ParamMap.newInstance().put("page",userPage).put("qo",qo));
    }

    private Page<UserInfo> createUserPage(SearchQueryObject qo){
        return searchService.searchWithHighlight(UserInfoEs.INDEX_NAME, UserInfoEs.TYPE_NAME,
                UserInfo.class, qo, "city", "info");
    }

    private Object searchTravel(SearchQueryObject qo) {
        final Page<Travel> travelPage = this.createTravelPage(qo);
        return JsonResult.success(ParamMap.newInstance().put("page",travelPage).put("qo",qo));
    }

    private Page<Travel> createTravelPage(SearchQueryObject qo){
        Page<Travel> travels = searchService.searchWithHighlight(TravelEs.INDEX_NAME, TravelEs.TYPE_NAME,
                Travel.class, qo, "title", "summary");
        for (Travel t : travels) {
            t.setAuthor(userInfoService.getById(t.getAuthorId()));
        }
        return travels;
    }

    private Object searchStrategy(SearchQueryObject qo) {
        final Page<Strategy> strategies = this.createStrategyPage(qo);
        return JsonResult.success(ParamMap.newInstance().put("page",strategies).put("qo",qo));
    }

    private Page<Strategy> createStrategyPage(SearchQueryObject qo){
        return searchService.searchWithHighlight(StrategyEs.INDEX_NAME, StrategyEs.TYPE_NAME,
                Strategy.class, qo, "title", "subTitle", "summary");
    }

    private Object searchDest(SearchQueryObject qo) {

        final Page<UserInfo> userInfos = searchService.searchWithHighlight(UserInfoEs.INDEX_NAME, UserInfoEs.TYPE_NAME, UserInfo.class, qo,"city");
        final Page<Travel> travels = searchService.searchWithHighlight(TravelEs.INDEX_NAME, TravelEs.TYPE_NAME, Travel.class, qo,"destName");
        final Page<Strategy> strategyPage = searchService.searchWithHighlight(StrategyEs.INDEX_NAME, StrategyEs.TYPE_NAME, Strategy.class, qo,"destName");
        final Destination dest = destinationService.queryByName(qo.getKeyword());
        for (Travel t : travels) {
            t.setAuthor(userInfoService.getById(t.getAuthorId()));
        }
        //如果是， 查询该目的地下所有游记， 攻略， 用户
        SearchResultVO vo = new SearchResultVO();
        vo.setUsers(userInfos.getContent());
        vo.setTravels(travels.getContent());
        vo.setStrategys(strategyPage.getContent());
        vo.setTotal(userInfos.getTotalElements()+travels.getTotalElements()+strategyPage.getTotalElements());
        //如果不是， 直接提示
        //result   dest  qo
        return JsonResult.success(ParamMap.newInstance().put("result", vo).put("dest", dest).put("qo", qo));
    }

    private Page<Destination> createDestPage(SearchQueryObject qo){
        return searchService.searchWithHighlight(DestinationEs.INDEX_NAME, DestinationEs.TYPE_NAME,
                Destination.class, qo, "city", "info");
    }

}
