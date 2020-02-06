package cn.wolfcode.easymvc.bean;

import java.lang.reflect.Method;

//封装每一个处理请求的方法的信息
public class ControllerBean {
	private Class<?> controllerClass;//当前方法所属字节码
	private Method controllerMethod;//当前方法对象

	public ControllerBean(Class<?> controllerClass, Method controllerMethod) {
		this.controllerClass = controllerClass;
		this.controllerMethod = controllerMethod;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getControllerMethod() {
		return controllerMethod;
	}
}
