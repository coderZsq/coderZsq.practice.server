package cn.wolfcode.mybatis.hello.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {
	private int currentPage = 1;
	private int pageSize = 3;

	//分页查询: LIMIT  start,pageSize
	public int getStart() {
		return (currentPage - 1) * pageSize;
	}

	//如果字符串为空串,也应该设置为null
	public String empty2null(String str) {
		return hasLength(str) ? str : null;
	}

	public boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}

}
