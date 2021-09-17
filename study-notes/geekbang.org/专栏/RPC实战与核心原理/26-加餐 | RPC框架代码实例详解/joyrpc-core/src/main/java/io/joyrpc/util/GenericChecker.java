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

import java.lang.reflect.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static io.joyrpc.util.ClassUtils.*;

/**
 * 泛型检测
 */
public class GenericChecker {

    /**
     * 非静态方法
     */
    public static final Predicate<Method> NONE_STATIC_METHOD = (method -> !Modifier.isStatic(method.getModifiers()));

    /**
     * 可序列化字段
     */
    public static final Predicate<Field> NONE_STATIC_TRANSIENT_FIELD = (field -> {
        int modifiers = field.getModifiers();
        return !Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers);
    });

    /**
     * 唯一
     */
    protected Set<Type> uniques = new HashSet<>();

    /**
     * 检测所有公共方法设计的类型
     *
     * @param clazz     类型
     * @param predicate 断言
     * @param consumer  消费者
     */
    public void checkMethods(final Class clazz, final Predicate<Method> predicate,
                             final Consumer<ClassInfo> consumer) {
        if (clazz == null || consumer == null) {
            return;
        }
        GenericClass genericClass = getGenericClass(clazz);
        List<Method> methods = getPublicMethod(clazz);
        GenericMethod genericMethod;
        for (Method method : methods) {
            //静态方法不验证
            if (predicate == null || predicate.test(method)) {
                genericMethod = genericClass.get(method);
                checkReturnType(genericMethod, consumer);
                checkParameterTypes(genericMethod, consumer);
                checkExceptionTypes(genericMethod, consumer);
            }
        }
    }

    /**
     * 检测所有公共方法设计的类型
     *
     * @param genericClass 类型
     * @param predicate    断言
     * @param consumer     消费者
     */
    public void checkMethods(final GenericClass genericClass, final Predicate<Method> predicate,
                             final Consumer<ClassInfo> consumer) {
        if (genericClass == null || consumer == null) {
            return;
        }
        List<Method> methods = getPublicMethod(genericClass.getClazz());
        GenericMethod genericMethod;
        for (Method method : methods) {
            //静态方法不验证
            if (predicate == null || predicate.test(method)) {
                genericMethod = genericClass.get(method);
                checkReturnType(genericMethod, consumer);
                checkParameterTypes(genericMethod, consumer);
                checkExceptionTypes(genericMethod, consumer);
            }
        }
    }

    /**
     * 检查字段类型
     *
     * @param genericClass 泛型类型
     * @param predicate    断言
     * @param consumer     消费者
     */
    public void checkFields(final GenericClass genericClass, final Predicate<Field> predicate,
                            final Consumer<ClassInfo> consumer) {
        //检查字段
        List<Field> fields = getFields(genericClass.getClazz());
        GenericType genericType;
        for (Field field : fields) {
            if (predicate.test(field)) {
                genericType = genericClass.get(field);
                checkType(genericType, genericType.getGenericType(), Scope.FIELD, consumer);
            }
        }
    }

    /**
     * 检查返回值类型
     *
     * @param method   方法
     * @param consumer 消费者
     */
    protected void checkReturnType(final GenericMethod method, final Consumer<ClassInfo> consumer) {
        GenericType genericType = method.getReturnType();
        checkType(genericType, genericType.getGenericType(), Scope.RETURN, consumer);
    }

    /**
     * 检查参数类型
     *
     * @param method   方法
     * @param consumer 消费者
     */
    protected void checkParameterTypes(final GenericMethod method, final Consumer<ClassInfo> consumer) {
        Type[] types = method.getMethod().getGenericParameterTypes();
        if (types != null) {
            GenericType[] genericTypes = method.getParameters();
            GenericType genericType;
            for (int i = 0; i < types.length; i++) {
                genericType = genericTypes[i];
                checkType(genericTypes[i], genericType.getGenericType(), Scope.PARAMETER, consumer);
            }
        }
    }

    /**
     * 检查异常类型
     *
     * @param method   方法
     * @param consumer 消费者
     */
    protected void checkExceptionTypes(final GenericMethod method, final Consumer<ClassInfo> consumer) {
        Type[] types = method.getMethod().getGenericExceptionTypes();
        if (types != null) {
            GenericType[] genericTypes = method.getExceptions();
            GenericType genericType;
            for (int i = 0; i < types.length; i++) {
                genericType = genericTypes[i];
                checkType(genericType, genericType.getGenericType(), Scope.EXCEPTION, consumer);
            }
        }
    }

    /**
     * 检查类型
     *
     * @param genericType 泛型类型
     * @param type        类型
     * @param scope       作用域
     * @param consumer    消费者
     */
    protected void checkType(final GenericType genericType, final Type type,
                             final Scope scope,
                             final Consumer<ClassInfo> consumer) {
        if (type == null || !uniques.add(type)) {
            //已经检查过
            return;
        } else if (type instanceof Class) {
            checkClass((Class) type, scope, genericType, consumer);
        } else if (type instanceof ParameterizedType) {
            checkParameterizedType(genericType, (ParameterizedType) type, scope, consumer);
        } else if (type instanceof TypeVariable) {
            //变量
            checkTypeVariable(genericType, (TypeVariable) type, scope, consumer);
        } else if (type instanceof GenericArrayType) {
            //泛型数组
            checkType(genericType, ((GenericArrayType) type).getGenericComponentType(), scope, consumer);
        } else if (type instanceof WildcardType) {

        }
    }

    /**
     * 检查变量泛型
     *
     * @param genericType 泛型类型
     * @param type        变量类型
     * @param scope       作用域
     * @param consumer    消费者
     */
    protected void checkTypeVariable(final GenericType genericType, final TypeVariable type,
                                     final Scope scope, final Consumer<ClassInfo> consumer) {
        GenericType.Variable variable = genericType.getVariable(type.getName());
        checkType(genericType, variable == null ? null : variable.getGenericType(), scope, consumer);
    }

    /**
     * 检查参数类型
     *
     * @param genericType 泛型
     * @param type        参数类型
     * @param scope       作用域
     * @param consumer    消费者
     */
    protected void checkParameterizedType(final GenericType genericType, final ParameterizedType type,
                                          final Scope scope, final Consumer<ClassInfo> consumer) {
        //argTypes
        Type[] argTypes = type.getActualTypeArguments();
        //rawType
        Class rawType = (Class) type.getRawType();
        GenericType newType = genericType;
        if (argTypes != null && argTypes.length > 0) {
            //构造该类型的泛型变量信息
            TypeVariable<Class<?>>[] variables = rawType.getTypeParameters();
            newType = new GenericType(genericType.genericType, genericType.type);
            for (int i = 0; i < variables.length; i++) {
                newType.addVariable(new GenericType.Variable(variables[i].getName(), argTypes[i]));
            }
        }
        checkType(newType, rawType, scope, consumer);
        //泛型参数再检测一下，便于处理java集合类型
        if (argTypes != null && argTypes.length > 0) {
            for (Type actualTypeArgument : argTypes) {
                checkType(genericType, actualTypeArgument, scope, consumer);
            }
        }
    }

    /**
     * 检查类型
     *
     * @param clazz       类
     * @param scope       作用域
     * @param genericType 泛型类型
     * @param consumer    消费者
     */
    protected void checkClass(final Class clazz, final Scope scope, final GenericType genericType, final Consumer<ClassInfo> consumer) {
        //参数允许是Callback
        consumer.accept(new ClassInfo(clazz.isArray() ? clazz.getComponentType() : clazz, scope, genericType));
    }

    /**
     * 作用域
     */
    public enum Scope {
        /**
         * 参数
         */
        PARAMETER("parameter"),
        /**
         * 返回值
         */
        RETURN("return"),
        /**
         * 异常
         */
        EXCEPTION("exception"),
        /**
         * 字段
         */
        FIELD("field");

        String name;

        Scope(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 类型信息
     */
    public static class ClassInfo {
        /**
         * 类
         */
        protected Class<?> clazz;
        /**
         * 作用域
         */
        protected Scope scope;
        /**
         * 类变量
         */
        protected GenericType genericType;

        public ClassInfo(Class<?> clazz, Scope scope, GenericType genericType) {
            this.clazz = clazz;
            this.scope = scope;
            this.genericType = genericType;
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public Scope getScope() {
            return scope;
        }

        /**
         * 获取泛型类
         *
         * @return
         */
        public GenericClass getGenericClass() {
            Map<String, Type> variables = genericType.getVariables();
            return variables == null || variables.isEmpty() ? ClassUtils.getGenericClass(clazz) : new GenericClass(clazz, variables);
        }
    }

}
