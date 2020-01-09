package cn.wolfcode.createbean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import cn.wolfcode.createbean._01_constructor.Cat1;
import cn.wolfcode.createbean._02_static_factory.Cat2;
import cn.wolfcode.createbean._02_static_factory.Cat2Factory;
import cn.wolfcode.createbean._03_instance_factory.Cat3;
import cn.wolfcode.createbean._03_instance_factory.Cat3Factory;
import cn.wolfcode.createbean._04_factory_bean.Cat4;

@SpringJUnitConfig
public class App {

	@Autowired
	private Cat1 c1;
	@Autowired
	private Cat2 c2;
	@Autowired
	private Cat3 c3;
	@Autowired
	private Cat4 c4;
	@Autowired
	private Cat4 c44;

	@Test
	void testOld() throws Exception {
		//方式1:构造器
		Cat1 c1 = new Cat1();
		//方式2:静态工厂方法
		Cat2 c2 = Cat2Factory.createInstance();
		//方式3:实例工厂方法
		Cat3 c3 = new Cat3Factory().createInstance();
	}

	@Test
	void test() throws Exception {
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		System.out.println(c44);
	}
}
