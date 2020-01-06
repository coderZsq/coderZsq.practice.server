package cn.wolfcode.mybatis.plugin;

import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

//把一行数据封装成Map之后:{u_name=Stef, u_id=1, u_sn=abc}
//在对象中还是习惯使用驼峰表示法,不喜欢使用下划线风格的编程.期望Map中的key的下划线去掉,使用驼峰表示法
//{uName=Stef, uId=1, uSn=abc}
//来标注要对哪一个组件中的哪一个方法做拦截增强
//对ResultSetHandler组件中的handleResultSets(Statement st)方法做增强
@Intercepts(@Signature(//
		type = ResultSetHandler.class, method = "handleResultSets", //
		args = { Statement.class } //
))
public class CamelCaseInterceptor implements Interceptor {
	private Properties properties;
	//包装
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	//给拦截器设置配置参数,配置可以由使用拦截器的人来给定
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	//如何做增强的细节
	public Object intercept(Invocation invocation) throws Throwable {
		List<Object> list = (List<Object>) invocation.proceed();//放行
		for (Object obj : list) {
			if (!(obj instanceof Map)) {
				break;
			}
			//如果每一行数据的封装对象是Map的实例
			handleMap((Map<String, Object>) obj);
		}
		return list;
	}

	private void handleMap(Map<String, Object> map) {
		Set<String> keySet = new HashSet(map.keySet());
		for (String key : keySet) {
			//判断key是否是大写字母开头或者带有下划线
			if (key.startsWith("A") && key.endsWith("Z") || key.contains("_")) {
				Object value  = map.get(key);
				map.remove(key);
				
				String newKey = handleKey(key);
				map.put(newKey, value);
			}
		}
	}

	private String handleKey(String key) {
		StringBuilder sb = new StringBuilder(10);
		
		boolean findUnderLine = false;//是否找到下划线
		for (int index = 0; index < key.length(); index++) {
			char ch  = key.charAt(index);//每一个字母
			if(ch == '_') {
				findUnderLine = true;//找到
			}else {
				if(findUnderLine) {
					sb.append(Character.toUpperCase(ch));
					findUnderLine = false;
				}else {
					sb.append(Character.toLowerCase(ch));
				}
			}
		}
		return sb.toString();
	}
}
