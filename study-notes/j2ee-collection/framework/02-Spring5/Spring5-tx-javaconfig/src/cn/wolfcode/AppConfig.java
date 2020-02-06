package cn.wolfcode;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//当前项目的配置类,好比是applicationContext.xml
@Configuration					//标识当前类为一个配置类
@Import(DataSourceConfig.class)	//包含其他的配置类
@ComponentScan				//IoC注解解析器
@EnableTransactionManagement//事务注解解析器
public class AppConfig {

	//创建事务管理的Bean
	@Bean
	public DataSourceTransactionManager txManager(DataSource ds) {
		return new DataSourceTransactionManager(ds);
	}
}
