package org.geekbang.thinking.in.spring.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

/**
 * 动态 (更新) 资源 {@link MessageSource} 实现
 *
 * 实现步骤
 *
 * 1. 实现资源位置 ( Properties 文件)
 * 2. 初始化 Properties 对象
 * 3. 实现 AbstractMessageSource#resolveCode 方法
 *
 * @see MessageSource
 * @see AbstractMessageSource
 */
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private static final String resourcePath = "/META-INF/msg.properties";

    private static final String ENCODING = "UTF-8";

    private final Properties messageProperties;

    private ResourceLoader resourceLoader;

    public DynamicResourceMessageSource() {
        messageProperties = loadMessageProperties();
    }

    private Properties loadMessageProperties() {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(resourcePath);
        EncodedResource encodedResource = new EncodedResource(resource, ENCODING);
        Properties properties = new Properties();
        try (Reader reader = encodedResource.getReader()){
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = messageProperties.getProperty(code);
        if (StringUtils.hasText(messageFormatPattern)) {
            return new MessageFormat(messageFormatPattern, locale);
        }
        return null;
    }

    private ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) {
        DynamicResourceMessageSource messageSource = new DynamicResourceMessageSource();
        String message = messageSource.getMessage("name", new Object[]{}, Locale.getDefault());
        System.out.println(message);
    }
}
