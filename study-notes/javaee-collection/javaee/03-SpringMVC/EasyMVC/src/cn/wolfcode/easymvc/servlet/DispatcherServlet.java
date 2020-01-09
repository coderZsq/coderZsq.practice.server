package cn.wolfcode.easymvc.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wolfcode.easymvc.annotation.Controller;
import cn.wolfcode.easymvc.annotation.RequestMapping;
import cn.wolfcode.easymvc.bean.ControllerBean;
import cn.wolfcode.easymvc.bean.ModelAndView;
import cn.wolfcode.easymvc.util.ClassUtil;

//前端控制器,分发
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//存储每一个url对应一个Controller方法
	private Map<String, ControllerBean> urlMap = new HashMap<>();

	public void init() throws ServletException {
		//扫描哪些包中的使用Controller注解标注的类
		List<Class<?>> classList = ClassUtil.getClassListByAnnotation("cn.wolfcode.hello", Controller.class);
		for (Class<?> clazz : classList) {
			Method[] methods = clazz.getDeclaredMethods();
			if (methods != null && methods.length > 0) {
				//判断Controller类中每一个方法是否被RequestMapping标注
				for (Method method : methods) {
					RequestMapping rm = method.getAnnotation(RequestMapping.class);
					if (rm != null) {
						String url = rm.value();
						urlMap.put(url, new ControllerBean(clazz, method));
					}
				}
			}
		}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取请求的资源名称
		String requestURI = request.getRequestURI();
		if ("/favocon.ico".equals(requestURI)) {
			return;
		}
		//去掉最后一个点后面的内容
		if(requestURI!=null && requestURI.contains(".")) {
			requestURI = requestURI.substring(0, requestURI.lastIndexOf("."));
		}
		
		//根据请求的url,寻找对应的Controller方法的包装对象
		ControllerBean controllerBean = urlMap.get(requestURI);
		if (controllerBean == null) {
			request.getRequestDispatcher(requestURI).forward(request, response);
			return;
		}
		//获取当前请求对应的Controller类和对应的Controller方法
		Class<?> controllerClass = controllerBean.getControllerClass();
		Method controllerMethod = controllerBean.getControllerMethod();

		try {
			Object controllerObject = controllerClass.newInstance();
			//调用响应的Controller方法,返回ModelAndView对象
			Object ret = controllerMethod.invoke(controllerObject);
			if (ret != null && ret.getClass() == ModelAndView.class) {
				//处理响应
				this.handleResult(request, response, (ModelAndView) ret);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//处理响应
	private void handleResult(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws ServletException, IOException {
		String viewName = mv.getViewName();
		Map<String, Object> attrs = mv.getModel();
		for(Map.Entry<String, Object> entry:attrs.entrySet()) {
			request.setAttribute(entry.getKey(), entry.getValue());
		}
		String prefix="/WEB-INF/views/";
		String suffix=".jsp";
		//物理视图=前缀+逻辑视图+后缀
		//   /WEB-INF/views/welcome.jsp
		request.getRequestDispatcher(prefix+viewName+suffix).forward(request, response);

	}
}
