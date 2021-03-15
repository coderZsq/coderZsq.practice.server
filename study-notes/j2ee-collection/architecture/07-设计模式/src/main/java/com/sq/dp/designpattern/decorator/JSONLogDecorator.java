package com.sq.dp.designpattern.decorator;

import com.alibaba.fastjson.JSON;

public class JSONLogDecorator extends AbstractLogDecorator {
    public JSONLogDecorator(Logger logger) {
        super(logger);
    }

    public void jsonInfo(Object data) {
        // TODO data -> json
        String json = JSON.toJSONString(data);
        super.info(json);
    }

    public void jsonError(Object data) {
        // TODO data -> json
        String json = JSON.toJSONString(data);
        super.error(json);
    }
}
