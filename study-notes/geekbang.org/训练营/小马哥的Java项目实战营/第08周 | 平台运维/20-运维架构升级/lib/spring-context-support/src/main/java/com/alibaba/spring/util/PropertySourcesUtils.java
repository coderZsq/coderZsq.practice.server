package com.alibaba.spring.util;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.core.env.PropertySourcesPropertyResolver;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import static com.alibaba.spring.util.ObjectUtils.EMPTY_STRING_ARRAY;
import static java.util.Collections.unmodifiableMap;

/**
 * {@link PropertySources} Utilities
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see PropertySources
 * @since 2017.01.19
 */
public abstract class PropertySourcesUtils {

    /**
     * Get Sub {@link Properties}
     *
     * @param propertySources {@link PropertySource} Iterable
     * @param prefix          the prefix of property name
     * @return Map
     * @see Properties
     */
    public static Map<String, Object> getSubProperties(Iterable<PropertySource<?>> propertySources, String prefix) {

        MutablePropertySources mutablePropertySources = new MutablePropertySources();

        for (PropertySource<?> source : propertySources) {
            mutablePropertySources.addLast(source);
        }

        return getSubProperties(mutablePropertySources, prefix);

    }

    /**
     * Get Sub {@link Properties}
     *
     * @param environment {@link ConfigurableEnvironment}
     * @param prefix      the prefix of property name
     * @return Map
     * @see Properties
     */
    public static Map<String, Object> getSubProperties(ConfigurableEnvironment environment, String prefix) {
        return getSubProperties(environment.getPropertySources(), environment, prefix);
    }

    /**
     * Normalize the prefix
     *
     * @param prefix the prefix
     * @return the prefix
     */
    public static String normalizePrefix(String prefix) {
        return prefix.endsWith(".") ? prefix : prefix + ".";
    }

    /**
     * Get prefixed {@link Properties}
     *
     * @param propertySources {@link PropertySources}
     * @param prefix          the prefix of property name
     * @return Map
     * @see Properties
     * @since 1.0.3
     */
    public static Map<String, Object> getSubProperties(PropertySources propertySources, String prefix) {
        return getSubProperties(propertySources, new PropertySourcesPropertyResolver(propertySources), prefix);
    }

    /**
     * Get prefixed {@link Properties}
     *
     * @param propertySources  {@link PropertySources}
     * @param propertyResolver {@link PropertyResolver} to resolve the placeholder if present
     * @param prefix           the prefix of property name
     * @return Map
     * @see Properties
     * @since 1.0.3
     */
    public static Map<String, Object> getSubProperties(PropertySources propertySources, PropertyResolver propertyResolver, String prefix) {

        Map<String, Object> subProperties = new LinkedHashMap<String, Object>();

        String normalizedPrefix = normalizePrefix(prefix);

        Iterator<PropertySource<?>> iterator = propertySources.iterator();

        while (iterator.hasNext()) {
            PropertySource<?> source = iterator.next();
            for (String name : getPropertyNames(source)) {
                if (!subProperties.containsKey(name) && name.startsWith(normalizedPrefix)) {
                    String subName = name.substring(normalizedPrefix.length());
                    if (!subProperties.containsKey(subName)) { // take first one
                        Object value = source.getProperty(name);
                        if (value instanceof String) {
                            // Resolve placeholder
                            value = propertyResolver.resolvePlaceholders((String) value);
                        }
                        subProperties.put(subName, value);
                    }
                }
            }
        }

        return unmodifiableMap(subProperties);
    }

    /**
     * Get the property names as the array from the specified {@link PropertySource} instance.
     *
     * @param propertySource {@link PropertySource} instance
     * @return non-null
     * @since 1.0.10
     */
    public static String[] getPropertyNames(PropertySource propertySource) {
        String[] propertyNames = propertySource instanceof EnumerablePropertySource ?
                ((EnumerablePropertySource) propertySource).getPropertyNames() : null;

        if (propertyNames == null) {
            propertyNames = EMPTY_STRING_ARRAY;
        }

        return propertyNames;
    }
}
