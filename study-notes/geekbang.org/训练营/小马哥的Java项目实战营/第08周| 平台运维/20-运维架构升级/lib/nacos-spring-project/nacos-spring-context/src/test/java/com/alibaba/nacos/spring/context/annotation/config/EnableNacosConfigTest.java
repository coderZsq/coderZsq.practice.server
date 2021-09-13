/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.nacos.spring.context.annotation.config;

import static com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig.ACCESS_KEY_PLACEHOLDER;
import static com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig.CLUSTER_NAME_PLACEHOLDER;
import static com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig.CONTEXT_PATH_PLACEHOLDER;
import static com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig.ENCODE_PLACEHOLDER;
import static com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig.ENDPOINT_PLACEHOLDER;
import static com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig.NAMESPACE_PLACEHOLDER;
import static com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig.SECRET_KEY_PLACEHOLDER;
import static com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig.SERVER_ADDR_PLACEHOLDER;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

/**
 * {@link EnableNacosConfig} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.1.0
 */
public class EnableNacosConfigTest {

	@Test
	public void testPlaceholders() {
		Assert.assertEquals("${nacos.config.endpoint:${nacos.endpoint:}}",
				ENDPOINT_PLACEHOLDER);
		Assert.assertEquals("${nacos.config.namespace:${nacos.namespace:}}",
				NAMESPACE_PLACEHOLDER);
		Assert.assertEquals("${nacos.config.access-key:${nacos.access-key:}}",
				ACCESS_KEY_PLACEHOLDER);
		Assert.assertEquals("${nacos.config.secret-key:${nacos.secret-key:}}",
				SECRET_KEY_PLACEHOLDER);
		Assert.assertEquals("${nacos.config.server-addr:${nacos.server-addr:}}",
				SERVER_ADDR_PLACEHOLDER);
		Assert.assertEquals("${nacos.config.context-path:${nacos.context-path:}}",
				CONTEXT_PATH_PLACEHOLDER);
		Assert.assertEquals("${nacos.config.cluster-name:${nacos.cluster-name:}}",
				CLUSTER_NAME_PLACEHOLDER);
		Assert.assertEquals("${nacos.config.encode:${nacos.encode:UTF-8}}",
				ENCODE_PLACEHOLDER);
	}

	@Test
	public void testResolvePlaceholders() {
		testResolvePlaceholder(ENDPOINT_PLACEHOLDER, "nacos.config.endpoint",
				"test-value", "test-value");
		testResolvePlaceholder(ENDPOINT_PLACEHOLDER, "nacos.endpoint", "test-value",
				"test-value");
		testResolvePlaceholder(ENDPOINT_PLACEHOLDER, "", "test-value", "");

		testResolvePlaceholder(NAMESPACE_PLACEHOLDER, "nacos.config.namespace",
				"test-value", "test-value");
		testResolvePlaceholder(NAMESPACE_PLACEHOLDER, "nacos.namespace", "test-value",
				"test-value");
		testResolvePlaceholder(NAMESPACE_PLACEHOLDER, "", "test-value", "");

		testResolvePlaceholder(ACCESS_KEY_PLACEHOLDER, "nacos.config.access-key",
				"test-value", "test-value");
		testResolvePlaceholder(ACCESS_KEY_PLACEHOLDER, "nacos.access-key", "test-value",
				"test-value");
		testResolvePlaceholder(ACCESS_KEY_PLACEHOLDER, "", "test-value", "");

		testResolvePlaceholder(SECRET_KEY_PLACEHOLDER, "nacos.config.secret-key",
				"test-value", "test-value");
		testResolvePlaceholder(SECRET_KEY_PLACEHOLDER, "nacos.secret-key", "test-value",
				"test-value");
		testResolvePlaceholder(SECRET_KEY_PLACEHOLDER, "", "test-value", "");

		testResolvePlaceholder(SERVER_ADDR_PLACEHOLDER, "nacos.config.server-addr",
				"test-value", "test-value");
		testResolvePlaceholder(SERVER_ADDR_PLACEHOLDER, "nacos.server-addr", "test-value",
				"test-value");
		testResolvePlaceholder(SERVER_ADDR_PLACEHOLDER, "", "test-value", "");

		testResolvePlaceholder(CONTEXT_PATH_PLACEHOLDER, "nacos.config.context-path",
				"test-value", "test-value");
		testResolvePlaceholder(CONTEXT_PATH_PLACEHOLDER, "nacos.context-path",
				"test-value", "test-value");
		testResolvePlaceholder(CONTEXT_PATH_PLACEHOLDER, "", "test-value", "");

		testResolvePlaceholder(CLUSTER_NAME_PLACEHOLDER, "nacos.config.cluster-name",
				"test-value", "test-value");
		testResolvePlaceholder(CLUSTER_NAME_PLACEHOLDER, "nacos.cluster-name",
				"test-value", "test-value");
		testResolvePlaceholder(CLUSTER_NAME_PLACEHOLDER, "", "test-value", "");

		testResolvePlaceholder(ENCODE_PLACEHOLDER, "nacos.config.encode", "test-value",
				"test-value");
		testResolvePlaceholder(ENCODE_PLACEHOLDER, "nacos.encode", "test-value",
				"test-value");
		testResolvePlaceholder(ENCODE_PLACEHOLDER, "", "test-value", "UTF-8");
	}

	private void testResolvePlaceholder(String placeholder, String propertyName,
			String propertyValue, String expectValue) {
		MockEnvironment environment = new MockEnvironment();
		environment.setProperty(propertyName, propertyValue);
		String resolvedValue = environment.resolvePlaceholders(placeholder);
		Assert.assertEquals(expectValue, resolvedValue);
	}
}
