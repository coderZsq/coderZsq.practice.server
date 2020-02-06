package cn.wolfcode.mybatis.hello.service;

import cn.wolfcode.mybatis.hello.query.PageResult;
import cn.wolfcode.mybatis.hello.query.QueryObject;

public interface IEmployeeService {
	PageResult query(QueryObject qo);
}
