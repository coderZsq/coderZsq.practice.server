package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.enhance.MpPage;
import com.sq.jk.enhance.MpQueryWrapper;
import com.sq.jk.mapper.ProvinceMapper;
import com.sq.jk.pojo.po.Province;
import com.sq.jk.pojo.query.ProvinceQuery;
import com.sq.jk.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {
    @Autowired
    private ProvinceMapper mapper;

    @Override
    public void list(ProvinceQuery query) {
        MpQueryWrapper<Province> wrapper = new MpQueryWrapper<>();
        String keyword = query.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(Province::getName, keyword).or()
                    .like(Province::getPlate, keyword);
        }
        // 按照id降序
        wrapper.orderByDesc(Province::getId);

        mapper.selectPage(new MpPage<>(query), wrapper).updateQuery(query);
    }
}