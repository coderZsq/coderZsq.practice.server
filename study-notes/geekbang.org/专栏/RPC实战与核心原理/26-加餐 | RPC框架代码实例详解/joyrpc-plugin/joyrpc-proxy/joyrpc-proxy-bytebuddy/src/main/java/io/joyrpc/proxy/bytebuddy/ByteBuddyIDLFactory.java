package io.joyrpc.proxy.bytebuddy;

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

import io.joyrpc.extension.Extension;
import io.joyrpc.extension.condition.ConditionalOnClass;
import io.joyrpc.proxy.AbstractIDLFactory;
import io.joyrpc.util.IDLMethodDesc;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static io.joyrpc.proxy.IDLFactory.ORDER_BYTE_BUDDY;

@Extension(value = "bytebuddy", order = ORDER_BYTE_BUDDY)
@ConditionalOnClass({"net.bytebuddy.ByteBuddy"})
public class ByteBuddyIDLFactory extends AbstractIDLFactory {

    @Override
    protected Class<?> buildRequestClass(final Class<?> clz, final Method method, final Naming naming) {
        DynamicType.Builder<?> builder = new ByteBuddy().
                with(new SimpleNamingStrategy(naming.getFullName())).
                subclass(Object.class).
                implement(Serializable.class);
        for (Parameter parameter : method.getParameters()) {
            builder = builder.defineProperty(parameter.getName(), parameter.getParameterizedType());
        }
        //TODO 目前不能自定义方法体，无法实现MethodArgs接口
        return builder.make().load(Thread.currentThread().getContextClassLoader()).getLoaded();
    }

    @Override
    protected Class<?> buildResponseClass(final Class<?> clz, final Method method, final Naming naming) {
        DynamicType.Builder<?> builder = new ByteBuddy().
                with(new SimpleNamingStrategy(naming.getFullName())).
                subclass(Object.class).
                implement(Serializable.class).
                defineProperty(IDLMethodDesc.F_RESULT, method.getGenericReturnType());
        return builder.make().load(Thread.currentThread().getContextClassLoader()).getLoaded();
    }

}
