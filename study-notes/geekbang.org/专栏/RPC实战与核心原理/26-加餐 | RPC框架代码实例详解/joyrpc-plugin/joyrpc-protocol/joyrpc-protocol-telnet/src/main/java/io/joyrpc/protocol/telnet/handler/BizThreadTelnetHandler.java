/**
 *
 */
package io.joyrpc.protocol.telnet.handler;

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

import io.joyrpc.invoker.ServiceManager;
import io.joyrpc.thread.ThreadPool;
import io.joyrpc.transport.Server;
import io.joyrpc.transport.channel.Channel;
import io.joyrpc.transport.telnet.TelnetResponse;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.joyrpc.Plugin.JSON;

/**
 * 业务线程池处理器
 */
public class BizThreadTelnetHandler extends AbstractTelnetHandler {

    public static final String CALLBACK = "callback";

    public BizThreadTelnetHandler() {
        options = new Options()
                .addOption(HELP_SHORT, HELP_LONG, false, "show help message for command config")
                .addOption("p", "port", true, "the threads of server port")
                .addOption("i", "interval", true, "the interval of output")
                .addOption("c", "count", true, "number of outputs");
    }

    @Override
    public String type() {
        return "tp";
    }

    @Override
    public String description() {
        return "Display the thread pool information.";
    }

    @Override
    public String shortDescription() {
        return "Display the thread pool information.";
    }

    @Override
    public TelnetResponse telnet(Channel channel, String[] args) {
        CommandLine cmd = getCommand(options, args);
        if (cmd.hasOption(HELP_SHORT)) {
            return new TelnetResponse(help());
        } else if (cmd.getOptions().length == 0) {
            Map<String, Object> result = new HashMap<>(100);
            export(CALLBACK, ServiceManager.getCallbackPool(), result);
            export(ServiceManager.getServers(), result);
            return new TelnetResponse(JSON.get().toJSONString(result));
        } else {
            String port = cmd.getOptionValue("p", String.valueOf(channel.getLocalAddress().getPort()));
            int interval = Integer.parseInt(cmd.getOptionValue("i", "1000"));
            int count = Integer.parseInt(cmd.getOptionValue("c", "1"));
            if (interval < 100 || interval > 5000) {
                return new TelnetResponse("ERROR:interval must between 100 and 5000");
            } else if (count < 1 || count > 60) {
                return new TelnetResponse("ERROR:count must between 1 and 60");
            }

            ThreadPool pool = getWorkerPool(port);
            if (pool != null) {
                HashMap<String, Map<String, Object>> map = new HashMap<>(1);
                StringBuilder builder = new StringBuilder(100);
                //循环
                for (int i = 0; i < count; i++) {
                    if (i > 0) {
                        //休息一段时间
                        try {
                            Thread.sleep(interval);
                        } catch (InterruptedException e) {
                            return new TelnetResponse(LINE);
                        }
                    }
                    if (!channel.isActive()) {
                        //通道被关闭了
                        return new TelnetResponse(LINE);
                    } else {
                        map.put(port, pool.dump());
                        if (i != count - 1) {
                            //最后一个由循环外输出
                            builder.setLength(0);
                            builder.append(JSON.get().toJSONString(map)).append(LINE);
                            channel.send(builder.toString());
                        }
                    }
                }
                return new TelnetResponse(JSON.get().toJSONString(map));
            } else {
                return new TelnetResponse("ERROR:Not found threadpool:" + port);
            }

        }
    }

    /**
     * 获取线程池
     * @param name 名称
     * @return 线程池
     */
    protected ThreadPool getWorkerPool(final String name) {
        if (name == null) {
            return null;
        } else if (Character.isDigit(name.charAt(0))) {
            Server server = ServiceManager.getServer(Integer.parseInt(name));
            return server == null ? null : server.getWorkerPool();
        } else if (CALLBACK.equalsIgnoreCase(name)) {
            return ServiceManager.getCallbackPool();
        }
        return null;
    }

    /**
     * 输出服务的线程池信息
     * @param servers 服务列表
     * @param result 结果
     */
    protected void export(final List<Server> servers, final Map<String, Object> result) {
        servers.forEach(o -> export(o, result));
    }

    /**
     * 输出服务的线程池信息
     * @param server 服务
     * @param result 结果
     */
    protected void export(final Server server, final Map<String, Object> result) {
        export(String.valueOf(server.getLocalAddress().getPort()), server.getWorkerPool(), result);
    }

    /**
     * 线程池信息
     * @param name 名称
     * @param executor 线程池
     * @param result 结果
     */
    protected void export(final String name, final ThreadPool executor, final Map<String, Object> result) {
        result.put(name, executor.dump());
    }

}
