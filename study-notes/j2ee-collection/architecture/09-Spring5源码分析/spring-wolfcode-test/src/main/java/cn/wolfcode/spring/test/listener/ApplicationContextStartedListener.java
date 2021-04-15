package cn.wolfcode.spring.test.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Leon
 * @date 2021/3/25
 */
@Component
public class ApplicationContextStartedListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("spring 容器启动完成了。。。。。");
	}
}
