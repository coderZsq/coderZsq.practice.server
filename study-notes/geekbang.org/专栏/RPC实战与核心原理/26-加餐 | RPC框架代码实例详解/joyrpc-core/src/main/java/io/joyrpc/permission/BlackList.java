package io.joyrpc.permission;

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

import java.util.Collection;

/**
 * 黑名单
 */
public interface BlackList<T> {

    /**
     * 判断是否在黑名单中
     *
     * @param target
     * @return
     */
    boolean isBlack(T target);

    /**
     * 感知黑名单
     */
    @FunctionalInterface
    interface BlackListAware {

        /**
         * 更新黑名单
         *
         * @param blackList 黑名单
         */
        void updateBlack(Collection<String> blackList);

    }

}
