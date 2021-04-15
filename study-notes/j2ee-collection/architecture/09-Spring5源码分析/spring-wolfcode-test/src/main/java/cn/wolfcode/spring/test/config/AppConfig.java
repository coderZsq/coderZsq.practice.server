package cn.wolfcode.spring.test.config;

import cn.wolfcode.spring.test.domain.User;
import cn.wolfcode.spring.test.event.PayFailedEvent;
import cn.wolfcode.spring.test.event.PaySuccessEvent;
import cn.wolfcode.spring.test.service.IUserService;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.math.BigDecimal;

/**
 * 启用 aspectj 代理
 *
 * @author Leon
 */
//@EnableTransactionManagement
//@EnableAspectJAutoProxy(exposeProxy = true)
@Configuration
@ComponentScan(basePackages = "cn.wolfcode.spring.test")
public class AppConfig {

//	@Bean(initMethod = "init", destroyMethod = "destroy")
//	public User user() {
//		return new User("test", "123456");
//	}
//
//	@Bean
//	@Scope("prototype")
//	public IUserService userService() {
//		UserServiceImpl userService = new UserServiceImpl();
//		userService.setUser(this.user());
//		return userService;
//	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_test");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;
	}

	@Bean
	public ApplicationEventMulticaster applicationEventMulticaster() {
		SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
		// 设置异步事件线程池：开启异步事件
		eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
		return eventMulticaster;
	}

	public static void main(String[] args) {
		System.out.println("-----------准备启动容器-----------");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		System.out.println("-----------容器初始化完成，开始使用容器-----------");
		User user = ctx.getBean(User.class);
		System.out.println(user);
		System.out.println("-----------获取 Service Bean-----------");

		String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
		System.out.println();

		System.out.println("------------------------------ 早期 aop 测试：开始 ------------------------------");
//		IUserService userServiceProxy = ctx.getBean("userServiceProxy", IUserService.class);
//		// 默认的 advice 会对所有方法进行代理
//		// userServiceProxy.login("test", "aoppppppp");
//
//		// 启用方法匹配后：会被代理
//		userServiceProxy.login("test", "aoppppppp");
//		// 启用方法匹配后：因为没有配置，所以不会被代理
//		userServiceProxy.test();

		// 启用自动代理后，我们不需要再去获取特殊的 proxyBean，而是直接获取我们真正使用的 bean
		IUserService userService = ctx.getBean("userServiceImpl", IUserService.class);
		userService.test();

		System.out.println("------------------------------ 早期 aop 测试：接触 ------------------------------");

		System.out.println();

		// aop 测试 -----------------------
//		System.out.println("------------------------------ aop 测试：开始 ------------------------------");
//		IUserService bean = (IUserService) ctx.getBean("userServiceImpl");
//		IUserService bean1 = (IUserService) ctx.getBean("userServiceImpl");
//		System.out.println("=================>>>>>>" + (bean == bean1));
//		bean.test();
//		System.out.println("------------------------------ aop 测试：结束 ------------------------------");

		System.out.println();

		// 自己添加的注册器
//		ctx.addApplicationListener(new PaySuccessListener());

		System.out.println("------------测试spring事件机制：Begin--------------");
		// 发布支付成功事件
		ctx.publishEvent(new PaySuccessEvent(ctx, "1000248720181204", "295898209213825983250", new BigDecimal("10000.00")));
		// 发布支付失败事件
		ctx.publishEvent(new PayFailedEvent(ctx, "234085972304958093", "985302921094982983", "20090", "支付失败，您的余额已不足！"));
		System.out.println("------------测试spring事件机制：End--------------");

		// 关闭容器
		System.out.println("-----------准备关闭容器-----------");
		ctx.close();
	}
}
