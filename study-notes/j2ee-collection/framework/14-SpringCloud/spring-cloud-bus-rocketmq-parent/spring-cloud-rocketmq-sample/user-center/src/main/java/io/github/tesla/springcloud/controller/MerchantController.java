package io.github.tesla.springcloud.controller;

import com.google.common.collect.Maps;
import io.github.tesla.springcloud.MerchantChangeRemoteApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Map;

@RestController
@RequestMapping("user-center")
public class MerchantController {

    static Map<String, String> merchantKeyMap = Maps.newHashMap();
    @Autowired
    private ApplicationContext context;
    @Autowired
    private BusProperties bu;

    private String appId;

    @PostConstruct
    public void init() {
        merchantKeyMap.put("merchant_1", "123456789");
        merchantKeyMap.put("merchant_2", "123456789");
        appId = bu.getId();
    }

    @RequestMapping("queryKey/{merchantId}")
    @ResponseBody
    public Map queryKey(@PathVariable("merchantId") String merchantId) {
        Map<String, String> map = Maps.newHashMap();
        map.put(merchantId, merchantKeyMap.get(merchantId));
        return map;
    }

    @PostMapping("modifyKey/{merchantId}/{merchantKey}")
    @ResponseBody
    public Map modifyKey(@PathVariable("merchantId") String merchantId,
                         @PathVariable("merchantKey") String merchantKey) {
        merchantKeyMap.put(merchantId, merchantKey);
        Map<String, String> map = Maps.newHashMap();
        map.put(merchantId, merchantKeyMap.get(merchantId));
        //发布事件,第三个参数是是否指定应用，不指定则会发全部
        context.publishEvent(new MerchantChangeRemoteApplicationEvent(this, appId, null));
        return map;
    }

}