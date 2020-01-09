package cn.wolfcode.container;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class App {

	//使用BeanFactory
	/*
	 结论:BeanFactory有延迟初始化(懒:lazy)的特点,在创建Spring容器的时候,不会立马去创建容器中管理的Bean对象,
	 	而是要等到从容器中去获取对象的时候,才去创建对象.
	 ----------------------------------------
	构建Person....
	cn.wolfcode.container.Person@76a4d6c 
	 */
	@Test
	void testBeanFactory() throws Exception {
		Resource resource = new ClassPathResource("cn/wolfcode/container/ContainerTest-context.xml");
		BeanFactory factory = new XmlBeanFactory(resource);
		System.out.println("----------------------------------------");
		//Person p = factory.getBean("person", Person.class);
		//System.out.println(p);

	}

	//使用ApplicationContext
	/*
	 在创建Spring容器的时候,就会把容器中管理的bean立马初始化,而不会等到获取bean的时候才去初始化.
	  构建Person....
	----------------------------------------
	cn.wolfcode.container.Person@402e37bc
	 */
	@Test
	void testApplicationContext() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("cn/wolfcode/container/ContainerTest-context.xml");
		System.out.println("----------------------------------------");
		Person p = ctx.getBean("person", Person.class);
		System.out.println(p);
	}
}
