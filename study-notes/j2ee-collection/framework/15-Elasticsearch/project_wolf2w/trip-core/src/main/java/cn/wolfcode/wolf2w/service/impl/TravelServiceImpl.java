package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.domain.TravelContent;
import cn.wolfcode.wolf2w.exception.LogicException;
import cn.wolfcode.wolf2w.mapper.TravelContentMapper;
import cn.wolfcode.wolf2w.mapper.TravelMapper;
import cn.wolfcode.wolf2w.query.TravelCondition;
import cn.wolfcode.wolf2w.query.TravelQuery;
import cn.wolfcode.wolf2w.service.ITravelService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import cn.wolfcode.wolf2w.util.WrapperUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
* 游记服务接口实现
*/
@Service
@Transactional
public class TravelServiceImpl extends ServiceImpl<TravelMapper,Travel> implements ITravelService  {
    @Autowired
    private TravelContentMapper travelContentMapper;

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public IPage<Travel> queryPage(TravelQuery qo) {
        IPage<Travel> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());


        QueryWrapper<Travel> wrapper = Wrappers.<Travel>query()
                .eq(qo.getDestId() != null, "dest_id", qo.getDestId())
                .orderByDesc(qo.getOrderBy())
                ;

        //出行天数
        TravelCondition day = TravelCondition.DAY_MAP.get(qo.getDayType());
        TravelCondition con = TravelCondition.AVG_MAP.get(qo.getConsumeType());
        TravelCondition time = TravelCondition.TIME_MAP.get(qo.getTravelTimeType());
        if(day != null){
            wrapper.between(day != null,"day", day.getMin(), day.getMax());
        }
        //人均
        if(con != null){
            wrapper.between(con != null,"avg_consume", con.getMin(), con.getMax());
        }
        //旅游时间
        if(time != null){
            //比较的是travel_time时间类型中月份
            wrapper.between(time != null,"DATE_FORMAT(travel_time,'%m')", time.getMin(), time.getMax());
        }
        super.page(page, wrapper);
        for (Travel t: page.getRecords()) {
            t.setAuthor(userInfoService.getById(t.getAuthorId()));
        }
        return page;
    }
    @Override
    public TravelContent getContent(Long id) {
        return travelContentMapper.selectById(id);
    }

    @Override
    public void audit(Long id, int state) {

        //判断是否满足审核条件
        Travel travel = super.getById(id);
        if(travel == null || travel.getState() != Travel.STATE_WAITING){
            throw  new LogicException("参数异常");
        }
        if(Travel.STATE_RELEASE == state){
            //审核通过之后做啥
            //1:修改游记状态为发布状态
            travel.setState(Travel.STATE_RELEASE);
            //2:设置发布时间
            travel.setReleaseTime(new Date());
            //3:修改最后更新时间
            travel.setLastUpdateTime(new Date());
            //4:记录审核信息： 审核人， 审核时间， 审核对象id， 审核状态， 审核备注 。。。。。
            super.updateById(travel);
        }else if(Travel.STATE_REJECT == state){
            //审核拒绝之后做啥
            //1:修改游记状态为拒绝状态
            travel.setState(Travel.STATE_REJECT);
            //2:设置发布时间为null
            travel.setReleaseTime(null);
            //3:修改最后更新时间
            travel.setLastUpdateTime(new Date());
            //4:记录审核信息： 审核人， 审核时间， 审核对象id， 审核状态， 审核备注 。。。。。
            super.updateById(travel);
        }else{
            throw  new LogicException("参数异常");
        }
    }

    @Override
    public List<Travel> queryViewnumTop3(Long destId) {
        //1:目的地id
        //2:排序
        //3:limit 3
        QueryWrapper<Travel> wrapper = WrapperUtil.query(Travel.class)
                .eq("dest_id", destId)
                .orderByDesc("viewnum")
                .last("limit 3");
        return super.list(wrapper);
    }

    @Override
    public List<Travel> queryByDestId(Long destId) {
        QueryWrapper<Travel> wrapper = WrapperUtil.query(Travel.class)
                .eq("dest_id", destId);
        List<Travel> list = super.list(wrapper);
        for (Travel t: list) {
            t.setAuthor(userInfoService.getById(t.getAuthorId()));
        }
        return list;
    }
}
