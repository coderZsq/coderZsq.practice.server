package com.sq.orderserver.listener;

import com.sq.orderserver.event.RemoteAppEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RemoteAppEventListener implements ApplicationListener<RemoteAppEvent> {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void onApplicationEvent(RemoteAppEvent event) {
        // 发布到远程 --> 所有的product-server的服务器
        // 1 获取到服务id
        String serviceId = event.getServiceId();
        // 2 通过注册中心获取到所有的实例对象
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        System.out.println("instances = " + instances);
        // 3 遍历每个实例对象, 拼接url
        for (ServiceInstance instance : instances) {
            String url = "http://" + instance.getHost() + ":" + instance.getPort();
            url = url + "remote/receive/event";
            // 4 发送请求
            Map<String, Object> map = new HashMap<>();
            map.put("data", event.getSource());
            String result = restTemplate.postForObject(url, map, String.class);
            System.out.println("result = " + result);
        }
    }
}
