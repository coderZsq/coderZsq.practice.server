package io.joyrpc.protocol.message.heartbeat;

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

import io.joyrpc.health.HealthState;
import io.joyrpc.protocol.message.Response;

/**
 * 心跳应答
 *
 * @date: 2019 /3/15
 */
public interface HeartbeatResponse extends Response {

    /**
     * Get health state health state.
     *
     * @return the health state
     */
    HealthState getHealthState();

}
