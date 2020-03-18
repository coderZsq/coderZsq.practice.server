package com.coderZsq.crm.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class ExamplePlugin implements Interceptor {

    Properties properties = new Properties();

    // 真实业务处理方法
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        // 1 sql语句构造对象 包括全局配置, 绑定的sql
        MappedStatement mappedStatement = (MappedStatement) args[0];
        // 2 实际调用的参数 keyword
        Object parameter = args[1];
        // 3 RowBounds: 查询返回行数: 开始位置, 查询记录数
        RowBounds rowBounds = (RowBounds) args[2];
        // 4 结果处理器
        ResultHandler resultHandler = (ResultHandler) args[3];
        // 5 获取执行
        Executor executor = (Executor) invocation.getTarget();
        // 先获取到原来的sql
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        String sql = boundSql.getSql();
        sql += " limit 0,3";
        boundSql = new BoundSql(mappedStatement.getConfiguration(), sql, boundSql.getParameterMappings(), parameter);
        // 添加一个缓存的key
        CacheKey cacheKey = executor.createCacheKey(mappedStatement, parameter, rowBounds, boundSql);
        // 执行查询操作
        return executor.query(mappedStatement, parameter, rowBounds, resultHandler, cacheKey, boundSql);
    }

    // 对目标方法绑定拦截器
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
