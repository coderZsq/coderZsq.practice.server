package io.joyrpc.protocol.http;

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

import io.joyrpc.extension.ExtensionPoint;
import io.joyrpc.extension.ExtensionPointLazy;

/**
 * 扩展点
 */
public interface Plugin {

    /**
     * URL参数绑定
     */
    ExtensionPoint<URLBinding, String> URL_BINDING = new ExtensionPointLazy<>(URLBinding.class);

    /**
     * http控制器
     */
    ExtensionPoint<HttpController, String> HTTP_CONTROLLER = new ExtensionPointLazy<>(HttpController.class);

    /**
     * 内容控制器
     */
    ExtensionPoint<ContentTypeHandler, String> CONTENT_TYPE_HANDLER = new ExtensionPointLazy<>(ContentTypeHandler.class);
}
