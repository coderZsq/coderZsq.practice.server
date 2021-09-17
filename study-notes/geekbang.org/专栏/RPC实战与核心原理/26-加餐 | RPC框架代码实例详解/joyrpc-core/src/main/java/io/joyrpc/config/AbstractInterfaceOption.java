package io.joyrpc.config;

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

import io.joyrpc.Callback;
import io.joyrpc.annotation.CallbackArg;
import io.joyrpc.annotation.EnableTrace;
import io.joyrpc.cache.Cache;
import io.joyrpc.cache.CacheConfig;
import io.joyrpc.cache.CacheFactory;
import io.joyrpc.cache.CacheKeyGenerator;
import io.joyrpc.cache.CacheKeyGenerator.ExpressionGenerator;
import io.joyrpc.cluster.distribution.TimeoutPolicy;
import io.joyrpc.constants.ExceptionCode;
import io.joyrpc.exception.InitializationException;
import io.joyrpc.exception.MethodOverloadException;
import io.joyrpc.extension.Parametric;
import io.joyrpc.extension.URL;
import io.joyrpc.extension.URLOption;
import io.joyrpc.extension.WrapperParametric;
import io.joyrpc.invoker.CallbackMethod;
import io.joyrpc.permission.SerializerWhiteList;
import io.joyrpc.protocol.message.Invocation;
import io.joyrpc.protocol.message.RequestMessage;
import io.joyrpc.transaction.TransactionFactory;
import io.joyrpc.transaction.TransactionOption;
import io.joyrpc.util.GenericClass;
import io.joyrpc.util.GenericMethod;
import io.joyrpc.util.IDLMethod;
import io.joyrpc.util.MethodOption.NameKeyOption;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.MethodDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

import static io.joyrpc.GenericService.GENERIC;
import static io.joyrpc.Plugin.*;
import static io.joyrpc.constants.Constants.*;
import static io.joyrpc.context.Variable.VARIABLE;
import static io.joyrpc.permission.SerializerWhiteList.getGlobalWhitelist;
import static io.joyrpc.util.ClassUtils.*;

/**
 * 内部的接口选项
 */
public abstract class AbstractInterfaceOption implements InterfaceOption {

    /**
     * 接口类型
     */
    protected Class<?> interfaceClass;
    /**
     * 接口名称
     */
    protected String interfaceName;
    /**
     * url
     */
    protected URL url;
    /**
     * 接口的隐藏参数
     */
    protected Map<String, String> implicits;
    /**
     * 泛型方法名称
     */
    protected GenericClass genericClass;
    /**
     * 接口级别超时时间
     */
    protected int timeout;
    /**
     * 接口级别并发数配置
     */
    protected int concurrency;
    /**
     * 是否启用缓存
     */
    protected boolean cacheEnable;
    /**
     * 是否缓存空值
     */
    protected boolean cacheNullable;
    /**
     * 缓存容量
     */
    protected int cacheCapacity;
    /**
     * 缓存过期时间
     */
    protected int cacheExpireTime;
    /**
     * 缓存键生成器
     */
    protected String cacheKeyGenerator;
    /**
     * 缓存提供者
     */
    protected String cacheProvider;
    /**
     * 缓存工厂类
     */
    protected CacheFactory cacheFactory;
    /**
     * 事务提供者
     */
    protected TransactionFactory transactionFactory;
    /**
     * 是否要进行方法参数验证
     */
    protected boolean validation;
    /**
     * 全局开启跟踪标识
     */
    protected boolean trace;
    /**
     * 启用限流
     */
    protected boolean limiter;
    /**
     * 验证器
     */
    protected Validator validator;
    /**
     * 对象类描述符
     */
    protected BeanDescriptor beanDescriptor;
    /**
     * 令牌
     */
    protected String token;

    /**
     * 方法透传参数
     */
    protected NameKeyOption<InnerMethodOption> options;
    /**
     * 是否是泛型调用
     */
    protected boolean generic;
    /**
     * 是否启用假数据
     */
    protected boolean mock;
    /**
     * 是否有回调方法
     */
    protected boolean callback;
    /**
     * 是否关闭了
     */
    protected AtomicBoolean closed = new AtomicBoolean(false);

    /**
     * 构造函数
     *
     * @param interfaceClass 接口类
     * @param interfaceName  接口名称
     * @param url            URL
     */
    public AbstractInterfaceOption(final Class<?> interfaceClass, final String interfaceName, final URL url) {
        this.interfaceClass = interfaceClass;
        this.interfaceName = interfaceName;
        this.url = url;
        this.generic = GENERIC.test(interfaceClass);
    }

    /**
     * 建立选项
     */
    protected void buildOptions() {
        this.options = new NameKeyOption<>(generic ? null : interfaceClass, generic ? interfaceName : null, this::create);
    }

    /**
     * 构建参数
     */
    protected void setup() {
        this.genericClass = getGenericClass(interfaceClass);
        //方法级别的隐藏参数，保留以"."开头
        this.implicits = url.startsWith(String.valueOf(HIDE_KEY_PREFIX));
        this.timeout = url.getPositiveInt(TIMEOUT_OPTION);
        this.concurrency = url.getInteger(CONCURRENCY_OPTION);
        this.token = url.getString(HIDDEN_KEY_TOKEN);
        //缓存配置
        this.cacheEnable = url.getBoolean(CACHE_OPTION);
        this.cacheNullable = url.getBoolean(CACHE_NULLABLE_OPTION);
        this.cacheCapacity = url.getInteger(CACHE_CAPACITY_OPTION);
        this.cacheExpireTime = url.getInteger(CACHE_EXPIRE_TIME_OPTION);
        this.cacheKeyGenerator = url.getString(CACHE_KEY_GENERATOR_OPTION);
        this.cacheProvider = url.getString(CACHE_PROVIDER_OPTION);
        this.cacheFactory = CACHE.get(cacheProvider);
        //事务工厂
        this.transactionFactory = TRANSACTION_FACTORY.get();
        //默认是否认证
        this.validation = url.getBoolean(VALIDATION_OPTION);
        this.limiter = url.getBoolean(LIMITER_OPTION);
        this.mock = url.getBoolean(new URLOption<>(MOCK_KEY, () -> VARIABLE.getBoolean(MOCK_OPTION)));
        EnableTrace enableTrace = interfaceClass.getAnnotation(EnableTrace.class);
        //全局开关
        this.trace = url.getBoolean(TRACE_OPEN, enableTrace == null ? VARIABLE.getBoolean(TRACE_OPEN_OPTION) : enableTrace.value());
        if (!generic) {
            this.validator = Validation.buildDefaultValidatorFactory().getValidator();
            if (validator != null) {
                this.beanDescriptor = validator.getConstraintsForClass(interfaceClass);
            }
        }

    }


    @Override
    public void close() {
        if (closed.compareAndSet(false, true)) {
            doClose();
        }
    }

    /**
     * 关闭
     */
    protected void doClose() {

    }

    /**
     * 构建方法选项
     *
     * @param method 方法
     * @return 方法选项
     */
    protected InnerMethodOption create(final String method) {
        final String prefix = URL_METHOD_PREX + method + ".";
        return create(new WrapperParametric(url, method, METHOD_KEY_FUNC, key -> key.startsWith(prefix)));
    }

    /**
     * 构建方法选项
     *
     * @param parametric 参数
     * @return 方法选项
     */
    protected abstract InnerMethodOption create(final WrapperParametric parametric);

    /**
     * 获取方法隐藏参数，合并了接口的隐藏参数
     *
     * @param method 方法
     * @return 方法隐藏参数
     */
    protected Map<String, String> getImplicits(final String method) {
        Map<String, String> result = url.startsWith(METHOD_KEY_FUNC.apply(method, String.valueOf(HIDE_KEY_PREFIX)), (k, v) -> v.substring(k.length() - 1));
        if (result.isEmpty()) {
            return implicits;
        } else if (implicits.isEmpty()) {
            return result;
        } else {
            implicits.forEach(result::putIfAbsent);
            return result;
        }
    }

    /**
     * 获取回调方法
     *
     * @param methodName 方法名称
     * @return 回调方法对象
     */
    protected IDLMethod getMethod(final String methodName) {
        if (generic) {
            return null;
        }
        try {
            return getPublicMethod(interfaceClass, methodName, GRPC_TYPE_FUNCTION);
        } catch (NoSuchMethodException | MethodOverloadException e) {
            return null;
        }
    }

    /**
     * 获取回调方法
     *
     * @param method     方法
     * @param parametric 参数
     * @return 回调方法对象
     */
    protected CallbackMethod getCallback(final Method method, final Parametric parametric) {
        if (method == null) {
            return null;
        }

        Parameter result = null;
        Parameter parameter;
        Class<?> type;
        Parameter[] parameters = method.getParameters();
        //配置了回调参数
        int index = parametric.getPositive(CALLBACK_ARG_KEY, -1);
        if (index >= 0 && index < parameters.length) {
            parameter = parameters[index];
            type = parameter.getType();
            if (type.isInterface()) {
                //回调参数只能是接口
                result = parameter;
            }
        }
        //遍历确保只有一个回调参数
        for (int i = 0; i < parameters.length; i++) {
            parameter = parameters[i];
            type = parameter.getType();
            if (type.isInterface() &&
                    (Callback.class.isAssignableFrom(type)
                            || (parameter.getAnnotation(CallbackArg.class) != null))) {
                if (result != null && i != index) {
                    throw new InitializationException("Illegal callback parameter at methodName " + method.getName()
                            + ",just allow one callback parameter", ExceptionCode.COMMON_CALL_BACK_ERROR);
                }
                result = parameter;
                index = i;
            }
        }
        if (result == null) {
            return null;
        }
        callback = true;
        return new CallbackMethod(interfaceClass, method, index, result, genericClass);
    }

    /**
     * 获取方法参数验证器
     *
     * @param parametric 参数
     * @return 参数验证器
     */
    protected Validator getValidator(final WrapperParametric parametric) {
        //过滤掉了不验证的方法或泛型接口
        if (validator == null || !parametric.getBoolean(VALIDATION_OPTION.getName(), validation)) {
            return null;
        }
        try {
            Method method = getPublicMethod(interfaceClass, parametric.getName());
            //判断该方法上是有有验证注解
            MethodDescriptor descriptor = beanDescriptor.getConstraintsForMethod(method.getName(), method.getParameterTypes());
            return descriptor != null && descriptor.hasConstrainedParameters() ? validator : null;
        } catch (NoSuchMethodException | MethodOverloadException e) {
            return null;
        }
    }

    /**
     * 构造缓存策略
     *
     * @param parametric 参数
     * @return 缓存策略
     */
    protected CachePolicy getCachePolicy(final WrapperParametric parametric) {
        CachePolicy cachePolicy = null;
        //判断是否开启了缓存
        boolean enable = cacheFactory == null ? false : parametric.getBoolean(CACHE_OPTION.getName(), cacheEnable);
        if (enable) {
            //获取缓存键生成器
            CacheKeyGenerator generator = CACHE_KEY_GENERATOR.get(parametric.getString(CACHE_KEY_GENERATOR_OPTION.getName(), cacheKeyGenerator));
            if (generator != null) {
                //看看是否是表达式
                if (generator instanceof ExpressionGenerator) {
                    ExpressionGenerator gen = (ExpressionGenerator) generator;
                    gen.setParametric(parametric);
                    gen.setup();
                }
                //判断是否缓存空值
                //创建缓存
                CacheConfig<Object, Object> cacheConfig = CacheConfig.builder().
                        nullable(parametric.getBoolean(CACHE_NULLABLE_OPTION.getName(), cacheNullable)).
                        capacity(parametric.getInteger(CACHE_CAPACITY_OPTION.getName(), cacheCapacity)).
                        expireAfterWrite(parametric.getInteger(CACHE_EXPIRE_TIME_OPTION.getName(), cacheExpireTime)).
                        build();
                Cache<Object, Object> cache = cacheFactory.build(parametric.getName(), cacheConfig);
                cachePolicy = new CachePolicy(cache, generator);
            }
        }
        return cachePolicy;
    }

    /**
     * 获取跟踪ID
     *
     * @param method 方法
     * @return
     */
    protected String getSpanId(final Method method) {
        return method == null ? null : interfaceName + "." + method.getName();
    }

    /**
     * 获取方法选项
     *
     * @param methodName 方法名称
     * @return 方法选项
     */
    public InnerMethodOption getOption(final String methodName) {
        return options.get(methodName);
    }

    @Override
    public boolean isGeneric() {
        return generic;
    }

    @Override
    public boolean isCallback() {
        return callback;
    }

    @Override
    public boolean isMock() {
        return mock;
    }

    @Override
    public boolean isTrace() {
        return trace || predicate(option -> option.isTrace());
    }

    @Override
    public boolean isCache() {
        return cacheEnable || predicate(option -> option.getCachePolicy() != null);
    }

    @Override
    public boolean isValidation() {
        return !generic && validator != null && (validation || predicate(option -> option.getValidator() != null));
    }

    @Override
    public boolean isConcurrency() {
        return concurrency > 0 || predicate(option -> option.getConcurrency().getMax() > 0);
    }

    @Override
    public boolean isLimiter() {
        return limiter;
    }

    /**
     * 测试方法选项
     *
     * @param predicate 断言
     * @return 是否满足条件
     */
    protected boolean predicate(final Predicate<InnerMethodOption> predicate) {
        for (Map.Entry<String, InnerMethodOption> entry : options.getOptions().entrySet()) {
            if (predicate.test(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法选项
     */
    protected static abstract class InnerMethodOption implements MethodOption {
        /**
         * 方法
         */
        protected Method method;
        /**
         * 泛型方法
         */
        protected GenericMethod genericMethod;
        /**
         * 参数类型
         */
        protected ArgType argType;
        /**
         * 方法级别隐式传参，合并了接口的隐藏参数，只读
         */
        protected Map<String, ?> implicits;
        /**
         * 超时时间
         */
        protected int timeout;
        /**
         * 并发数配置
         */
        protected Concurrency concurrency;
        /**
         * 缓存策略
         */
        protected CachePolicy cachePolicy;
        /**
         * 方法参数验证器
         */
        protected Validator validator;
        /**
         * 事务选项
         */
        protected TransactionOption transactionOption;
        /**
         * 令牌
         */
        protected String token;
        /**
         * 是否异步调用
         */
        protected boolean async;
        /**
         * 是否开启跟踪
         */
        protected transient boolean trace;
        /**
         * 跟踪的span名称
         */
        protected String traceSpanId;
        /**
         * 回调方法
         */
        protected CallbackMethod callback;
        /**
         * 参数描述
         */
        protected String description;
        /**
         * 序列化白名单
         */
        protected SerializerWhiteList whiteList;

        /**
         * 构造函数
         *
         * @param idlMethod    GRPC方法
         * @param genericMethod 泛型方法
         * @param implicits     隐式传参
         * @param timeout       超时时间
         * @param concurrency   并发数配置
         * @param cachePolicy   缓存策略
         * @param validator     方法参数验证器
         * @param token         令牌
         * @param async         判断方法是否是异步调用
         * @param callback      回调方法
         */
        public InnerMethodOption(final IDLMethod idlMethod,
                                 final GenericMethod genericMethod,
                                 final Map<String, ?> implicits, int timeout,
                                 final Concurrency concurrency,
                                 final CachePolicy cachePolicy,
                                 final Validator validator,
                                 final TransactionOption transactionOption,
                                 final String token,
                                 final boolean async,
                                 final boolean trace,
                                 final CallbackMethod callback) {
            //只有泛化调用的时候没有设置grpMethod
            this.method = idlMethod == null ? null : idlMethod.getMethod();
            this.genericMethod = genericMethod;
            Class<?>[] types = method == null ? null : method.getParameterTypes();
            //采用canonicalName是为了和泛化调用保持一致，可读性和可写行更好
            this.argType = method == null ? null : new ArgType(types, getCanonicalNames(types), idlMethod.getSupplier());
            this.implicits = implicits == null ? null : Collections.unmodifiableMap(implicits);
            this.timeout = timeout;
            this.concurrency = concurrency;
            this.cachePolicy = cachePolicy;
            this.validator = validator;
            this.transactionOption = transactionOption;
            this.token = token;
            this.async = async;
            this.trace = trace;
            this.traceSpanId = method == null ? null : getTraceSpanId(idlMethod.getClazz().getName(), method.getName());
            this.callback = callback;
            this.description = getDesc(types);
            this.whiteList = getGlobalWhitelist();
        }

        @Override
        public Method getMethod() {
            return method;
        }

        @Override
        public GenericMethod getGenericMethod() {
            return genericMethod;
        }

        @Override
        public ArgType getArgType() {
            return argType;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public Map<String, ?> getImplicits() {
            return implicits;
        }

        @Override
        public int getTimeout() {
            return timeout;
        }

        @Override
        public Concurrency getConcurrency() {
            return concurrency;
        }

        @Override
        public CachePolicy getCachePolicy() {
            return cachePolicy;
        }

        @Override
        public Validator getValidator() {
            return validator;
        }

        @Override
        public TransactionOption getTransactionOption() {
            return transactionOption;
        }

        @Override
        public String getToken() {
            return token;
        }

        @Override
        public boolean isAsync() {
            return async;
        }

        @Override
        public boolean isTrace() {
            return trace;
        }

        public void setTrace(boolean trace) {
            this.trace = trace;
        }

        /**
         * 获取跟踪ID
         *
         * @param className  类
         * @param methodName 方法
         * @return 跟踪ID
         */
        protected String getTraceSpanId(final String className, final String methodName) {
            return className + "/" + methodName;
        }

        @Override
        public String getTraceSpanId(final Invocation invocation) {
            if (traceSpanId != null) {
                return traceSpanId;
            }
            //泛化调用
            return getTraceSpanId(invocation.getClassName(), invocation.getArgs()[0].toString());
        }

        @Override
        public CallbackMethod getCallback() {
            return callback;
        }

    }

    /**
     * 超时策略
     */
    public static class MyTimeoutPolicy implements TimeoutPolicy {

        @Override
        public boolean test(final RequestMessage<Invocation> request) {
            return request.isTimeout();
        }

        @Override
        public void decline(final RequestMessage<Invocation> request) {
            request.decline();
        }
    }


}
