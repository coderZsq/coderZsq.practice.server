package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.domain.Strategy;
import cn.wolfcode.wolf2w.redis.service.IStrategyStatisVORedisService;
import cn.wolfcode.wolf2w.redis.util.RedisKeys;
import cn.wolfcode.wolf2w.redis.vo.StrategyStatisVO;
import cn.wolfcode.wolf2w.service.IStrategyService;
import cn.wolfcode.wolf2w.util.DateUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class StrategyStatisVORedisServiceImpl implements IStrategyStatisVORedisService {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private IStrategyService strategyService;

    @Override
    public void viewnumIncrease(Long sid) {
        //1:拼接vo的key
        //strategy_statis_vo:sid
        StrategyStatisVO vo = this.getStrategyStatisVO(sid);
        //5:阅读数 +1
        vo.setViewnum(vo.getViewnum() + 1);
        //6:更新vo
        this.setStrategyStatisVO(vo);
    }

    @Override
    public void setStrategyStatisVO(StrategyStatisVO vo) {
        String key = RedisKeys.STRATEGY_STATIS_VO.join(vo.getStrategyId().toString());
        template.opsForValue().set(key, JSON.toJSONString(vo));
    }

    @Override
    public StrategyStatisVO getStrategyStatisVO(Long sid) {
        String key = RedisKeys.STRATEGY_STATIS_VO.join(sid.toString());
        StrategyStatisVO vo = new StrategyStatisVO();
        //2:判断vo的key是否存在
        if(!template.hasKey(key)){
            //3:如果不存在，初始化
            //查询mysql库
            Strategy strategy = strategyService.getById(sid);
            BeanUtils.copyProperties(strategy, vo);
            vo.setStrategyId(strategy.getId());
            template.opsForValue().set(key, JSON.toJSONString(vo));
        }else{
            //4:如果存在，获取
            String voStr = template.opsForValue().get(key);
            vo = JSON.parseObject(voStr, StrategyStatisVO.class);
        }
        return vo;
    }
    @Override
    public void replynumIncrease(Long sid) {
        //拼接key
        //判断key是否存在， 存在直接获取， 不存在初始化
        StrategyStatisVO vo = this.getStrategyStatisVO(sid);
        //执行+1操作
        vo.setReplynum(vo.getReplynum() + 1);
        //更新
        this.setStrategyStatisVO(vo);
    }


    @Override
    public boolean favor(Long sid, Long uid) {

        //拼接用户收藏攻略id集合： sids list   key
        String key = RedisKeys.USER_STRATEGY_FAVOR.join(uid.toString());
        //判断key是否存在， 存在获取，不存在初始化
        List<Long> sids = new ArrayList<>();  //因为mysql中没有收藏表设计，暂时使用new方式
        if(template.hasKey(key)){
            String sidsStr = template.opsForValue().get(key);
            //参数1：json格式字符串(是list格式)  参数2：集合中泛型
            sids = JSON.parseArray(sidsStr, Long.class);
        }

        boolean flag = false;
        //判断sid是否在sids集合中
        StrategyStatisVO vo = this.getStrategyStatisVO(sid);
        if(sids.contains(sid)){
            //如果在, 表示取消收藏， 收藏数-1，sid移除出sids集合
            vo.setFavornum(vo.getFavornum() -1);
            sids.remove(sid);
        }else{
            //如果不在，表示收藏操作， 收藏 +1，sid添加到sids集合中
            vo.setFavornum(vo.getFavornum() +1);
            sids.add(sid);
            flag = true;
        }
        //更新sids vo对象
        template.opsForValue().set(key, JSON.toJSONString(sids));
        this.setStrategyStatisVO(vo);
        //return flag;
        return sids.contains(sid);
    }

    @Override
    public List<Long> getStrategyIdList(Long uid) {
        //拼接用户收藏攻略id集合： sids list   key
        String key = RedisKeys.USER_STRATEGY_FAVOR.join(uid.toString());
        //判断key是否存在， 存在获取，不存在初始化
        List<Long> sids = new ArrayList<>();  //因为mysql中没有收藏表设计，暂时使用new方式
        if(template.hasKey(key)){
            String sidsStr = template.opsForValue().get(key);
            //参数1：json格式字符串(是list格式)  参数2：集合中泛型
            sids = JSON.parseArray(sidsStr, Long.class);
        }
        return sids;
    }

    @Override
    public boolean strategyThumbup(Long sid, Long uid) {

        //1:拼接标记的key
        //user_strategy_thumb:uid:sid
        String key = RedisKeys.USER_STRATEGY_THUMB.join(uid.toString(), sid.toString());
        //2:判断key是否存在，
        //存在不做任何操作
        if(!template.hasKey(key)){
            //不存在， 点赞数+1， 缓存key
            StrategyStatisVO vo = this.getStrategyStatisVO(sid);
            vo.setThumbsupnum(vo.getThumbsupnum() + 1);
            //更新 vo
            this.setStrategyStatisVO(vo);
            //缓存key
            Date now = new Date();
            Date end = DateUtil.getEndDate(now);
            long time = DateUtil.getDateBetween(now, end);  //当前时间今天最后一秒
            template.opsForValue().set(key, "1", time, TimeUnit.SECONDS);

            return true;
        }
        return false;
    }

    @Override
    public boolean isVoExists(Long sid) {
        String key = RedisKeys.STRATEGY_STATIS_VO.join(sid.toString());
        return template.hasKey(key);
    }

    @Override
    public List<StrategyStatisVO> queryByPattern(String pattern) {
        List<StrategyStatisVO> vos = new ArrayList<>();
        //strategy_statis_vo:*
        String p = RedisKeys.STRATEGY_STATIS_VO.join(pattern);
        //keys  strategy_statis_vo:*
        Set<String> voKeys = template.keys(p);
        if(voKeys!= null && voKeys.size() > 0){
            for (String voKey : voKeys) {
                String voStr = template.opsForValue().get(voKey);
                vos.add(JSON.parseObject(voStr, StrategyStatisVO.class));
            }
        }
        return vos;
    }
}
