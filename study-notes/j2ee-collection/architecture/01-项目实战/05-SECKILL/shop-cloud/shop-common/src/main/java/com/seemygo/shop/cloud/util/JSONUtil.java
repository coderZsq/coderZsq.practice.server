package com.seemygo.shop.cloud.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

    private final static ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper();

    public static String toJSONString(Object obj) {
        try {
            return DEFAULT_OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        try {
            return DEFAULT_OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
