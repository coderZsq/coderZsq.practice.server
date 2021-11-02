package io.joyrpc.util;

/*-
 * #%L
 * joyrpc
 * %%
 * Copyright (C) 2019 joyrpc.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 泛型方法
 */
public class GenericMethod extends GenericExecutable<Method> {

    /**
     * 返回值泛型type名称映射
     */
    protected static final Map<String, Type> GENERIC_RETURN_TYPES = new ConcurrentHashMap<>(5000);

    /**
     * 返回值泛型
     */
    protected GenericType returnType;

    /**
     * 构造函数
     *
     * @param method     方法
     * @param parameters 泛型参数
     * @param exceptions 泛型异常
     * @param returnType 泛型返回值
     */
    public GenericMethod(final Method method,
                         final GenericType[] parameters,
                         final GenericType[] exceptions,
                         final GenericType returnType) {
        super(method, parameters, exceptions);
        this.returnType = returnType;
        Type type = this.returnType.getGenericType();
        GENERIC_RETURN_TYPES.put(type.getTypeName(), type);
    }

    public GenericType getReturnType() {
        return returnType;
    }

    /**
     * 通过名称获取返回值泛型类型
     *
     * @param typeName 类型名称
     * @return 类型
     */
    public static Type getReturnGenericType(final String typeName) {
        return GENERIC_RETURN_TYPES.get(typeName);
    }

}
