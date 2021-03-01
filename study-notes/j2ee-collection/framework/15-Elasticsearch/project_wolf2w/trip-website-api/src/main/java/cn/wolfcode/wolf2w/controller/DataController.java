package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.search.domain.DestinationEs;
import cn.wolfcode.wolf2w.search.domain.StrategyEs;
import cn.wolfcode.wolf2w.search.domain.TravelEs;
import cn.wolfcode.wolf2w.search.domain.UserInfoEs;
import cn.wolfcode.wolf2w.search.service.IDestinationEsService;
import cn.wolfcode.wolf2w.search.service.IStrategyEsService;
import cn.wolfcode.wolf2w.search.service.ITravelEsService;
import cn.wolfcode.wolf2w.search.service.IUserInfoEsService;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.service.ITravelService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import lombok.AllArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RestController
@AllArgsConstructor
public class DataController {

    private ITravelService travelService;
    private IUserInfoService userInfoService;
    private IDestinationService destinationService;
    private IStrategyService strategyService;

    private ITravelEsService travelEsService;
    private IUserInfoEsService userInfoEsService;
    private IDestinationEsService destinationEsService;
    private IStrategyEsService strategyEsService;


    @RequestMapping("/dataInit")
    public String dataInit() throws InvocationTargetException, IllegalAccessException {
        //从数据库查询所有的游记, 目的地, 用户, 攻略的数据, 在依次插入到数据库
        //游记
        final List<Travel> travels = travelService.list();
        for (Travel travel : travels) {
            final TravelEs travelEs = new TravelEs();
            BeanUtils.copyProperties(travelEs,travel);
            travelEsService.save(travelEs);
        }
        //攻略
        final List<Strategy> sts = strategyService.list();
        for (Strategy st : sts) {
            final StrategyEs strategyEs = new StrategyEs();
            BeanUtils.copyProperties(strategyEs,st);
            strategyEsService.save(strategyEs);
        }
        //目的地
        final List<Destination> dests = destinationService.list();
        for (Destination dest : dests) {
            final DestinationEs destEs = new DestinationEs();
            BeanUtils.copyProperties(destEs,dest);
            destinationEsService.save(destEs);
        }
        //用户
        final List<UserInfo> userInfos = userInfoService.list();
        for (UserInfo userInfo : userInfos) {
            final UserInfoEs userInfoEs = new UserInfoEs();
            BeanUtils.copyProperties(userInfoEs,userInfo);
            userInfoEsService.save(userInfoEs);
        }
        return "success";
    }
}
