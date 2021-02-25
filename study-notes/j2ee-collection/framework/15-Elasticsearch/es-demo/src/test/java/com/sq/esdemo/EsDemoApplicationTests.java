package com.sq.esdemo;

import com.sq.esdemo.dao.ProductRepository;
import com.sq.esdemo.domain.Product;
import com.sq.esdemo.service.IProductService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsDemoApplicationTests {

	@Autowired
	private IProductService productService;

	@Autowired
	private ProductRepository repository;

	@Test
	public void testSave() {
		Product p = new Product();
		p.setBrand("苹果");
		p.setIntro("苹果手机, iPhone X");
		p.setPrice(10000);
		p.setTitle("全球最差的手机");
		p.setId("007");
		productService.save(p);
	}

	@Test
	public void testUpdate() {
		Product p = new Product();
		p.setId("007");
		p.setBrand("ooxx");
		p.setIntro("1234");
		p.setPrice(9990);
		p.setTitle("全球最nb的手机");
		productService.update(p);
	}

	@Test
	public void testDelete() {
		productService.delete("007");
	}

	@Test
	public void testGet() {
		System.out.println(productService.get("007"));
	}

	@Test
	public void testList() {
		System.out.println(productService.list());
	}

	// 查询商品标题中符合"游戏 手机"的字样的商品

	/*
	  "query": {
	  	"match": {"title": "游戏 手机"}
	  }*/
	@Test
	public void test() throws Exception {
		// SearchQuery --> NativeSearchQuery
		// NativeSearchQuery
		final MatchQueryBuilder queryBuilder = new MatchQueryBuilder("title", "游戏 手机");
		NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryBuilder);
		final Page<Product> page = repository.search(nativeSearchQuery);
		for (Product product : page.getContent()) {
			System.err.println("product = " + product);
		}

		System.err.println("===========================");

		final Iterable<Product> products = repository.search(queryBuilder);
		for (Product product : products) {
			System.err.println(product);
		}

		System.err.println("===========================");

		// 方案三
		final Page<Product> result = repository.search(queryBuilder, PageRequest.of(1, 3));
		for (Product product : result.getContent()) {
			System.err.println("product = " + product);
		}

		System.err.println("===========================");

		// 方案四: 推荐, 构建者模式
		NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
		builder.withQuery(
				QueryBuilders.matchQuery("title", "游戏 手机")
		);
		builder.withPageable(PageRequest.of(1, 3));
		final NativeSearchQuery build = builder.build();
		final Page<Product> result2 = repository.search(build);
		for (Product product : result2.getContent()) {
			System.err.println("product = " + product);
		}
	}

	// 查询商品标题或简介中符合"蓝牙 指纹 双卡"的字样的商品
	/**
	 * "query": {
	 *     "multi_match": {
	 *         "query": "蓝牙 指纹 双卡".
	 *         "fields": ["title", "intro"]
	 *     }
	 * }
	 */
	@Test
	public void test2() throws Exception {
		// 方案四: 推荐, 构建者模式
		NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
		builder.withQuery(
				QueryBuilders.multiMatchQuery("蓝牙 指纹 双卡", "title", "intro")
		);
		builder.withPageable(PageRequest.of(1, 3));
		final NativeSearchQuery build = builder.build();
		final Page<Product> result2 = repository.search(build);
		for (Product product : result2.getContent()) {
			System.err.println("product = " + product);
		}
	}

	@Test
	public void test3() throws Exception {
		final Page<Product> page = repository.queryByTitle("小米9", PageRequest.of(1, 3));
		for (Product product : page.getContent()) {
			System.err.println(product);
		}
	}
}
