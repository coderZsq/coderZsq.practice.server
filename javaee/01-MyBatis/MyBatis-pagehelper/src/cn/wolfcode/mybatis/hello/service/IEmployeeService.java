package cn.wolfcode.mybatis.hello.service;

import com.github.pagehelper.PageInfo;

import cn.wolfcode.mybatis.hello.query.QueryObject;

public interface IEmployeeService {
	
	
	PageInfo<?> query(QueryObject qo);
}
