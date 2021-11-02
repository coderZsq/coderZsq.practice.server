package io.joyrpc.expression;

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

import io.joyrpc.extension.Extensible;

/**
 * 表达式引擎
 */
@Extensible("expressionProvider")
public interface ExpressionProvider {

    int MVEL_ORDER = 90;

    int SPEL_ORDER = MVEL_ORDER + 10;

    int JEXL_ORDER = SPEL_ORDER + 10;

    /**
     * 构建表达式
     *
     * @param expression 表达式描述
     * @return 表达式对象
     */
    Expression build(String expression);

}
