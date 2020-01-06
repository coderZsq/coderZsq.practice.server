package cn.wolfcode.easymvc.bean;

import java.util.HashMap;
import java.util.Map;

//模型对象和视图对象合体
//模型:需要共享的数据
//视图:跳转的页面
public class ModelAndView {
	private String viewName;//视图名称
	private Map<String, Object> model = new HashMap<>();

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public void addAttribute(String key, String value) {
		this.model.put(key, value);
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
