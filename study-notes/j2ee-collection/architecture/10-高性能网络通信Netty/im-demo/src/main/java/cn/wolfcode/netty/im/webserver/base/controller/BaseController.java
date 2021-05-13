package cn.wolfcode.netty.im.webserver.base.controller;

import com.alibaba.fastjson.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leon
 */
public class BaseController {

    public Object putMsgToJsonString(int code, String msg) {
        return putMsgToJsonString(code, msg, null);
    }

    public Object putMsgToJsonString(int code, Object data) {
        return putMsgToJsonString(code, "success.", data);
    }

    /**
     * @param code  1 成功  0 失败
     * @param msg   消息内容
     * @param data  具体内容
     * @return
     */
    public Object putMsgToJsonString(int code, String msg, Object data) {
        Map<String, Object> map = new HashMap<String, Object>(10);
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        return JSONArray.toJSON(map);
    }
}
