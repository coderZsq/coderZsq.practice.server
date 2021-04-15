/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.lang.Nullable;

/**
 * Delegate for AbstractApplicationContext's post-processor handling.
 * 调用bean工厂的后置处理器
 * 1)BeanDefinitionRegistryPostProcessor(先被执行)
 *   所有的bean定义信息将要被加载到容器中，Bean实例还没有被初始化
 * 2)BeanFactoryPostProcessor(后执行)
 *   所有的Bean定义信息已经加载到容器中，但是Bean实例还没有被初始化.
 * 该方法的的作用就是用于ioc容器加载bean定义前后进行处理
 * BeanDefinitionRegistryPostProcessor是bean定义解析前调用
 * 	   1)实现了PriorityOrdered接口的
 * 	   2)实现了Ordered接口的
 * 	   3)没有实现任何的优先级接口的
 * 	   4)因为BeanDefinitionRegistryPostProcessor是BeanFactoryPostProcessor接口的子接口，实现BeanFactoryPostProcessor的方法
 * BeanFactoryPostProcessor是bean定义解析后调用
 *     1)实现了PriorityOrdered接口的
 * 	   2)实现了Ordered接口的
 * 	   3)没有实现任何的优先级接口的
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 4.0
 */
final class PostProcessorRegistrationDelegate {

	private PostProcessorRegistrationDelegate() {
	}


	public static void invokeBeanFactoryPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {

		// Invoke BeanDefinitionRegistryPostProcessors first, if any.
		// 首先调用实现了 BeanDefinitionRegistryPostProcessors 的处理器
		Set<String> processedBeans = new HashSet<>();

		// 判断我们的 beanFactory 实现了 BeanDefinitionRegistry (实现了该结构就有注册和获取 Bean 定义的能力）
		if (beanFactory instanceof BeanDefinitionRegistry) {
			// 强行把我们的 bean 工厂转为 BeanDefinitionRegistry，因为待会需要注册 Bean 定义
			BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
			// 保存 BeanFactoryPostProcessor 类型的后置 BeanFactoryPostProcessor 提供修改
			List<BeanFactoryPostProcessor> regularPostProcessors = new ArrayList<>();
			// 保存 BeanDefinitionRegistryPostProcessor 类型的后置处理器 BeanDefinitionRegistryPostProcessor 提供注册
			List<BeanDefinitionRegistryPostProcessor> registryProcessors = new ArrayList<>();

			// 循环我们传递进来的 beanFactoryPostProcessors（默认没有）
			for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessors) {
				// 判断我们的后置处理器是不是 BeanDefinitionRegistryPostProcessor
				if (postProcessor instanceof BeanDefinitionRegistryPostProcessor) {
					// 进行强制转化
					BeanDefinitionRegistryPostProcessor registryProcessor =
							(BeanDefinitionRegistryPostProcessor) postProcessor;
					// 调用他作为 BeanDefinitionRegistryPostProcessor 的处理器的后置方法
					registryProcessor.postProcessBeanDefinitionRegistry(registry);
					// 添加到我们用于保存的 BeanDefinitionRegistryPostProcessor 的集合中
					registryProcessors.add(registryProcessor);
				}
				else {
					// 若没有实现 BeanDefinitionRegistryPostProcessor 接口，那么他就是 BeanFactoryPostProcessor
					// 把当前的后置处理器加入到 regularPostProcessors 中
					regularPostProcessors.add(postProcessor);
				}
			}

			// Do not initialize FactoryBeans here: We need to leave all regular beans
			// uninitialized to let the bean factory post-processors apply to them!
			// Separate between BeanDefinitionRegistryPostProcessors that implement
			// PriorityOrdered, Ordered, and the rest.
			// 用于保存当前准备创建的 BeanDefinitionRegistryPostProcessor
			List<BeanDefinitionRegistryPostProcessor> currentRegistryProcessors = new ArrayList<>();

			// First, invoke the BeanDefinitionRegistryPostProcessors that implement PriorityOrdered.
			// 第一步: 获取容器中所有实现了 BeanDefinitionRegistryPostProcessors，此时只处理优先级最高的处理器，既实现了 PriorityOrdered
			String[] postProcessorNames =
					beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			for (String ppName : postProcessorNames) {
				// 判断是否实现了 PriorityOrdered 接口的
				if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
					// 显示的调用 getBean() 的方式获取出该对象然后加入到 currentRegistryProcessors 集合中去
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					// 同时也加入到 processedBeans 集合中去
					processedBeans.add(ppName);
				}
			}

			// 对 currentRegistryProcessors 集合中 BeanDefinitionRegistryPostProcessor 进行排序
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			// 把当前的加入到总的注册处理器中里面去
			registryProcessors.addAll(currentRegistryProcessors);
			// 在这里典型的 BeanDefinitionRegistryPostProcessor 就是 ConfigurationClassPostProcessor
			// 用于进行 bean 定义的加载 比如我们的包扫描 @ComponentScan、@import  等等。。。
			// 调用 BeanDefinitionRegistryPostProcessor 处理器（ConfigurationClassPostProcessor 在此被调用）
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			// 调用完之后，马上 clear 掉
			currentRegistryProcessors.clear();

			// ============ 调用内置实现 PriorityOrdered 接口 ConfigurationClassPostProcessor 完毕--优先级No1-End =================================================

			// Next, invoke the BeanDefinitionRegistryPostProcessors that implement Ordered.
			// 下一步: 获取容器中所有实现了 BeanDefinitionRegistryPostProcessors，此时次级自定义优先级的处理器，实现了 Ordered 接口
			postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
			// 循环上一步获取的 BeanDefinitionRegistryPostProcessor 的类型名称
			for (String ppName : postProcessorNames) {
				// 表示没有被处理过,且实现了 Ordered 接口的
				if (!processedBeans.contains(ppName) && beanFactory.isTypeMatch(ppName, Ordered.class)) {
					// 显示的调用 getBean() 的方式获取出该对象然后加入到 currentRegistryProcessors 集合中去
					currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
					// 同时也加入到 processedBeans 集合中去
					processedBeans.add(ppName);
				}
			}
			// 对 currentRegistryProcessors 集合中 BeanDefinitionRegistryPostProcessor 进行排序
			sortPostProcessors(currentRegistryProcessors, beanFactory);
			// 把他加入到用于保存到 registryProcessors 中
			registryProcessors.addAll(currentRegistryProcessors);
			// 调用这些后置处理器
			invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
			// 调用完之后，马上 clear 掉
			currentRegistryProcessors.clear();

			// ============= 调用自定义 Order 接口 BeanDefinitionRegistryPostProcessor 完毕-优先级 No2-End ========================================================

			// Finally, invoke all other BeanDefinitionRegistryPostProcessors until no further ones appear.
			// 最后：调用没有实现任何优先级接口的 BeanDefinitionRegistryPostProcessor
			// 定义一个重复处理的开关变量默认值为 true
			boolean reiterate = true;
			// 第一次就可以进来
			while (reiterate) {
				// 进入循环马上把开关变量给改为 false
				reiterate = false;
				// 获取到容器中所有的 BeanDefinitionRegistryPostProcessor，并且只调用之前没调用过的
				postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
				// 循环上一步获取的 BeanDefinitionRegistryPostProcessor 的类型名称
				for (String ppName : postProcessorNames) {
					// 之前没调用过才会在此处调用
					if (!processedBeans.contains(ppName)) {
						// 显示的调用 getBean() 的方式获取出该对象然后加入到 currentRegistryProcessors 集合中去
						currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
						// 同时也加入到 processedBeans 集合中去
						processedBeans.add(ppName);
						// 再次设置为 true
						reiterate = true;
					}
				}
				// 对 currentRegistryProcessors 集合中 BeanDefinitionRegistryPostProcessor 进行排序
				sortPostProcessors(currentRegistryProcessors, beanFactory);
				// 把他加入到用于保存到 registryProcessors 中
				registryProcessors.addAll(currentRegistryProcessors);
				// 调用这些后置处理器
				invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
				// 进行 clear
				currentRegistryProcessors.clear();
			}
			// ============== 调用没有实现任何优先级接口自定义 BeanDefinitionRegistryPostProcessor 完毕--End ========================================================

			// Now, invoke the postProcessBeanFactory callback of all processors handled so far.
			// 调用所有优先级的 BeanFactoryPostProcessor 的 postProcessBeanFactory 方法
			invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);
			// 调用第一优先级的 BeanFactoryPostProcessor 的 postProcessBeanFactory 方法
			invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory);
		}

		else {
			// Invoke factory processors registered with the context instance.
			// 若当前的 beanFactory 没有实现了 BeanDefinitionRegistry 说明没有注册 Bean 定义的能力
			// 那么就直接调用 BeanFactoryPostProcessor.postProcessBeanFactory 方法
			invokeBeanFactoryPostProcessors(beanFactoryPostProcessors, beanFactory);
		}
		// ================== 所有 BeanDefinitionRegistryPostProcessor 调用完毕 --End =============================================================================

		// ======================================================== 处理 BeanFactoryPostProcessor --Begin ========================================================
		// Do not initialize FactoryBeans here: We need to leave all regular beans
		// uninitialized to let the bean factory post-processors apply to them!
		// 获取容器中所有的 BeanFactoryPostProcessor
		String[] postProcessorNames =
				beanFactory.getBeanNamesForType(BeanFactoryPostProcessor.class, true, false);

		// Separate between BeanFactoryPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		// 保存 BeanFactoryPostProcessor 类型实现了 priorityOrdered
		List<BeanFactoryPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		// 保存 BeanFactoryPostProcessor 类型实现了 Ordered 接口的
		List<String> orderedPostProcessorNames = new ArrayList<>();
		// 保存 BeanFactoryPostProcessor 没有实现任何优先级接口的
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
		for (String ppName : postProcessorNames) {
			// processedBeans 包含的话，表示在上面处理 BeanDefinitionRegistryPostProcessor 的时候处理过了
			if (processedBeans.contains(ppName)) {
				// skip - already processed in first phase above
			}
			// 判断是否实现了 PriorityOrdered 优先级最高
			else if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanFactoryPostProcessor.class));
			}
			// 判断是否实现了 Ordered 优先级其次
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				orderedPostProcessorNames.add(ppName);
			}
			// 没有实现任何的优先级接口的 最后调用
			else {
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, invoke the BeanFactoryPostProcessors that implement PriorityOrdered.
		// 排序
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		// 先调用 BeanFactoryPostProcessor 实现了 PriorityOrdered 接口的
		invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);

		// Next, invoke the BeanFactoryPostProcessors that implement Ordered.
		// 再调用实现了 Ordered 的 BeanFactoryPostProcessor
		List<BeanFactoryPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
		for (String postProcessorName : orderedPostProcessorNames) {
			orderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		// 排序
		sortPostProcessors(orderedPostProcessors, beanFactory);
		// 调用 BeanFactoryPostProcessor.postProcessBeanFactory 方法
		invokeBeanFactoryPostProcessors(orderedPostProcessors, beanFactory);

		// Finally, invoke all other BeanFactoryPostProcessors.
		// 最后，调用没有实现任何方法接口的
		List<BeanFactoryPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
		for (String postProcessorName : nonOrderedPostProcessorNames) {
			nonOrderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
		}
		// 调用 BeanFactoryPostProcessor.postProcessBeanFactory 方法
		invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory);
		// ============== 处理 BeanFactoryPostProcessor --End ==================================================================================================

		// Clear cached merged bean definitions since the post-processors might have
		// modified the original metadata, e.g. replacing placeholders in values...
		beanFactory.clearMetadataCache();
		// ============== BeanFactoryPostProcessor 和 BeanDefinitionRegistryPostProcessor 调用完毕 --End ========================================================
	}

	/**
	 * 给我们容器中注册了我们 bean 的后置处理器
	 * bean 的后置处理器在什么时候进行调用？在 bean 的各个生命周期中都会进行调用
	 * @param beanFactory
	 * @param applicationContext
	 */
	public static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, AbstractApplicationContext applicationContext) {

		// 去容器中获取所有的 BeanPostProcessor 的名称(还是 bean 定义)
		String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

		// Register BeanPostProcessorChecker that logs an info message when
		// a bean is created during BeanPostProcessor instantiation, i.e. when
		// a bean is not eligible for getting processed by all BeanPostProcessors.
		/**
		 * bean 的后置处理器的个数 beanFactory.getBeanPostProcessorCount() 成品的个数 之前refresh-->prepareBeanFactory() 中注册的
		 * postProcessorNames.length beanFactory 工厂中 bean 定义的个数 +1 在后面又马上注册了 BeanPostProcessorChecker 的后置处理器
		 */
		int beanProcessorTargetCount = beanFactory.getBeanPostProcessorCount() + 1 + postProcessorNames.length;
		beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));

		// Separate between BeanPostProcessors that implement PriorityOrdered,
		// Ordered, and the rest.
		// 按照 BeanPostProcessor 实现的优先级接口来分离我们的后置处理器
		// 保存实现了 priorityOrdered 接口的
		List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
		// 系统内部的
		List<BeanPostProcessor> internalPostProcessors = new ArrayList<>();
		// 实现了我们 ordered 接口的
		List<String> orderedPostProcessorNames = new ArrayList<>();
		// 没有优先级的
		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
		// 循环我们的 bean 定义(BeanPostProcessor)
		for (String ppName : postProcessorNames) {
			// 若实现了 PriorityOrdered 接口的
			if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
				// 显示的调用 getBean 流程创建 bean 的后置处理器
				BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
				// 加入到集合中
				priorityOrderedPostProcessors.add(pp);
				// 判断是否实现了 MergedBeanDefinitionPostProcessor
				if (pp instanceof MergedBeanDefinitionPostProcessor) {
					// 加入到集合中
					internalPostProcessors.add(pp);
				}
			}
			// 判断是否实现了 Ordered
			else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
				orderedPostProcessorNames.add(ppName);
			}
			// 没有任何实现接口的
			else {
				nonOrderedPostProcessorNames.add(ppName);
			}
		}

		// First, register the BeanPostProcessors that implement PriorityOrdered.
		// 首先，把实现了 priorityOrdered 注册到容器中
		sortPostProcessors(priorityOrderedPostProcessors, beanFactory);
		registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);

		// Next, register the BeanPostProcessors that implement Ordered.
		// 然后，处理实现 Ordered 的 bean 定义
		List<BeanPostProcessor> orderedPostProcessors = new ArrayList<>(orderedPostProcessorNames.size());
		for (String ppName : orderedPostProcessorNames) {
			// 显示调用 getBean 方法
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			// 加入到集合中
			orderedPostProcessors.add(pp);
			// 判断是否实现了 MergedBeanDefinitionPostProcessor
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				// 加入到集合中
				internalPostProcessors.add(pp);
			}
		}
		// 排序并且注册我们实现了 Order 接口的后置处理器
		sortPostProcessors(orderedPostProcessors, beanFactory);
		registerBeanPostProcessors(beanFactory, orderedPostProcessors);

		// Now, register all regular BeanPostProcessors.
		// 实例化我们所有的非排序接口的
		List<BeanPostProcessor> nonOrderedPostProcessors = new ArrayList<>(nonOrderedPostProcessorNames.size());
		for (String ppName : nonOrderedPostProcessorNames) {
			// 显示调用
			BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
			nonOrderedPostProcessors.add(pp);
			// 判断是否实现了 MergedBeanDefinitionPostProcessor
			if (pp instanceof MergedBeanDefinitionPostProcessor) {
				internalPostProcessors.add(pp);
			}
		}
		// 注册我们普通的没有实现任何排序接口的
		registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);

		// Finally, re-register all internal BeanPostProcessors.
		// 注册 MergedBeanDefinitionPostProcessor 类型的后置处理器 bean 合并后的处理，Autowired 注解正是通过此方法实现诸如类型的预解析
		sortPostProcessors(internalPostProcessors, beanFactory);
		registerBeanPostProcessors(beanFactory, internalPostProcessors);

		// Re-register post-processor for detecting inner beans as ApplicationListeners,
		// moving it to the end of the processor chain (for picking up proxies etc).
		// 注册 ApplicationListenerDetector 应用监听器探测器的后置处理器
		beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
	}

	private static void sortPostProcessors(List<?> postProcessors, ConfigurableListableBeanFactory beanFactory) {
		// Nothing to sort?
		if (postProcessors.size() <= 1) {
			return;
		}
		Comparator<Object> comparatorToUse = null;
		if (beanFactory instanceof DefaultListableBeanFactory) {
			comparatorToUse = ((DefaultListableBeanFactory) beanFactory).getDependencyComparator();
		}
		if (comparatorToUse == null) {
			comparatorToUse = OrderComparator.INSTANCE;
		}
		postProcessors.sort(comparatorToUse);
	}

	/**
	 * Invoke the given BeanDefinitionRegistryPostProcessor beans.
	 */
	private static void invokeBeanDefinitionRegistryPostProcessors(
			Collection<? extends BeanDefinitionRegistryPostProcessor> postProcessors, BeanDefinitionRegistry registry) {

		// 获取容器中的 ConfigurationClassPostProcessor 的后置处理器进行 bean 定义的扫描
		for (BeanDefinitionRegistryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanDefinitionRegistry(registry);
		}
	}

	/**
	 * Invoke the given BeanFactoryPostProcessor beans.
	 */
	private static void invokeBeanFactoryPostProcessors(
			Collection<? extends BeanFactoryPostProcessor> postProcessors, ConfigurableListableBeanFactory beanFactory) {

		for (BeanFactoryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanFactory(beanFactory);
		}
	}

	/**
	 * Register the given BeanPostProcessor beans.
	 */
	private static void registerBeanPostProcessors(
			ConfigurableListableBeanFactory beanFactory, List<BeanPostProcessor> postProcessors) {

		for (BeanPostProcessor postProcessor : postProcessors) {
			beanFactory.addBeanPostProcessor(postProcessor);
		}
	}


	/**
	 * BeanPostProcessor that logs an info message when a bean is created during
	 * BeanPostProcessor instantiation, i.e. when a bean is not eligible for
	 * getting processed by all BeanPostProcessors.
	 */
	private static final class BeanPostProcessorChecker implements BeanPostProcessor {

		private static final Log logger = LogFactory.getLog(BeanPostProcessorChecker.class);

		private final ConfigurableListableBeanFactory beanFactory;

		private final int beanPostProcessorTargetCount;

		public BeanPostProcessorChecker(ConfigurableListableBeanFactory beanFactory, int beanPostProcessorTargetCount) {
			this.beanFactory = beanFactory;
			this.beanPostProcessorTargetCount = beanPostProcessorTargetCount;
		}

		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) {
			return bean;
		}

		@Override
		public Object postProcessAfterInitialization(Object bean, String beanName) {
			if (!(bean instanceof BeanPostProcessor) && !isInfrastructureBean(beanName) &&
					this.beanFactory.getBeanPostProcessorCount() < this.beanPostProcessorTargetCount) {
				if (logger.isInfoEnabled()) {
					logger.info("Bean '" + beanName + "' of type [" + bean.getClass().getName() +
							"] is not eligible for getting processed by all BeanPostProcessors " +
							"(for example: not eligible for auto-proxying)");
				}
			}
			return bean;
		}

		private boolean isInfrastructureBean(@Nullable String beanName) {
			if (beanName != null && this.beanFactory.containsBeanDefinition(beanName)) {
				BeanDefinition bd = this.beanFactory.getBeanDefinition(beanName);
				return (bd.getRole() == RootBeanDefinition.ROLE_INFRASTRUCTURE);
			}
			return false;
		}
	}

}
