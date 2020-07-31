package io.github.tesla.springcloud.controller;

import com.google.common.collect.Maps;
import io.github.tesla.springcloud.MerchantChangeRemoteApplicationEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("osg-gateway")
public class MerchantController {

    private final static Log log = LogFactory.getLog(MerchantController.class);
    static Map<String, String> merchantKeyMap = Maps.newHashMap();
    private String userCenterUrl = "http://localhost:8902";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("queryKey/{merchantId}")
    @ResponseBody
    public Map queryKey(@PathVariable("merchantId") String merchantId) {
        if (StringUtils.isEmpty(merchantKeyMap.get(merchantId))) {
            Map<String, String> resultMap = restTemplate.getForObject(userCenterUrl + "/user-center/queryKey/" + merchantId, Map.class);
            merchantKeyMap.put(merchantId, resultMap.get(merchantId));
        }
        Map<String, String> map = Maps.newHashMap();
        map.put(merchantId, merchantKeyMap.get(merchantId));
        return map;
    }

    @EventListener(MerchantChangeRemoteApplicationEvent.class)
    public void refreshMerchantKey() {
        log.info("receive merchantChangeEvent ,begin refresh merchantKey cache");
        merchantKeyMap.clear();
    }

}