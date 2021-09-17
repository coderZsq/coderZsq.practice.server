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
import java.util.function.Supplier;

/**
 * 接口描述语言对应的方法信息
 */
public class IDLMethod {
    /**
     * 类型
     */
    protected Class<?> clazz;
    /**
     * 方法
     */
    protected Method method;
    /**
     * 类型提供者
     */
    protected Supplier<IDLMethodDesc> supplier;

    public IDLMethod(Class<?> clazz, Method method, Supplier<IDLMethodDesc> supplier) {
        this.clazz = clazz;
        this.method = method;
        this.supplier = supplier;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Method getMethod() {
        return method;
    }

    public Supplier<IDLMethodDesc> getSupplier() {
        return supplier;
    }

    public IDLMethodDesc getType() {
        return supplier.get();
    }
}
