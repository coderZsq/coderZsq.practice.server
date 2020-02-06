package cn.wolfcode.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
//<bean id="someBean" class="cn.wolfcode.lifecycle.SomeBean" scope="singleton"
//		init-method="open" destroy-method="close"/>
@Component
@Scope("singleton")
public class SomeBean {

	public SomeBean() {
		System.out.println("构建SomeBean对象");
	}

	@PostConstruct//构建的对象之后
	public void open() {
		System.out.println("初始化方法");
	}

	@PreDestroy//销毁之前
	public void close() {
		System.out.println("销毁前扫尾方法");
	}

	public void doWork() {
		System.out.println("工作");
	}
}
