package io.joyrpc.cluster.distribution.loadbalance.adaptive.judge;

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

import io.joyrpc.cluster.Node;
import io.joyrpc.cluster.distribution.loadbalance.adaptive.AdaptivePolicy;
import io.joyrpc.cluster.distribution.loadbalance.adaptive.NodeMetric;
import io.joyrpc.cluster.distribution.loadbalance.adaptive.Rank;

/**
 * 服务状态评分，包括是否熔断
 */
public class ServerStatusJudge extends AbstractJudge {

    public ServerStatusJudge() {
        super(JudgeType.ServerStatus);
    }

    @Override
    public Rank score(final NodeMetric metric, final AdaptivePolicy policy) {
        if (metric.isBroken()) {
            return Rank.Disabled;
        }
        Node node = metric.getNode();
        switch (node.getHealth()) {
            case NORMAL:
                return Rank.Good;
            case ABNORMAL:
                return Rank.Poor;
            default:
                return Rank.Disabled;
        }
    }

}
