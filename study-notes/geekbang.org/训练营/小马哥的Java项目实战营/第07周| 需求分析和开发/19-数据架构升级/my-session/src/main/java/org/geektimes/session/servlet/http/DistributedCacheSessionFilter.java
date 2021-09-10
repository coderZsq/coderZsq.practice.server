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
package org.geektimes.session.servlet.http;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.geektimes.configuration.microprofile.config.source.servlet.FilterConfigSource;
import org.geektimes.configuration.microprofile.config.util.DelegatingPropertiesAdapter;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;

/**
 * {@link HttpSession} Filter based on the distributed cache.
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 * Date : 2021-04-28
 */
public class DistributedCacheSessionFilter implements Filter {

    public static final String CACHE_URI_PROPERTY_NAME = "javax.cache.CacheManager.uri";

    private ClassLoader classLoader;

    private Config config;

    private CacheManager cacheManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.classLoader = filterConfig.getServletContext().getClassLoader();
        this.config = buildConfig(filterConfig, classLoader);
        this.cacheManager = buildCacheManager(config, classLoader);
    }


    protected Config buildConfig(FilterConfig filterConfig, ClassLoader classLoader) {
        ConfigProviderResolver resolver = ConfigProviderResolver.instance();
        ConfigBuilder configBuilder = resolver.getBuilder();
        configBuilder.forClassLoader(classLoader);
        configBuilder.addDefaultSources();
        configBuilder.addDiscoveredSources();
        configBuilder.addDiscoveredConverters();
        configBuilder.withSources(new FilterConfigSource(filterConfig));
        return configBuilder.build();
    }

    private CacheManager buildCacheManager(Config config, ClassLoader classLoader) {
        URI uri = config.getValue(CACHE_URI_PROPERTY_NAME, URI.class);
        CachingProvider cachingProvider = Caching.getCachingProvider(classLoader);
        return cachingProvider.getCacheManager(uri, classLoader,new DelegatingPropertiesAdapter(config));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) { // Non-HTTP Servlet
            chain.doFilter(request, response);
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        doFilter(httpRequest, httpResponse, chain);
    }

    protected void doFilter(HttpServletRequest request, HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
