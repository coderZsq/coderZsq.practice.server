package com.seemygo.shop.cloud.web;

import com.seemygo.shop.cloud.resp.CodeMsg;

public class SeckillCodeMsg extends CodeMsg {

    private SeckillCodeMsg(String code, String msg) {
        super(code, msg);
    }

    public static final SeckillCodeMsg OP_ERROR = new SeckillCodeMsg("A003001", "秒杀失败");
    public static final SeckillCodeMsg NOT_START_ERROR = new SeckillCodeMsg("A003002", "秒杀尚未开始");
    public static final SeckillCodeMsg END_ERROR = new SeckillCodeMsg("A003003", "秒杀已经结束");
    public static final SeckillCodeMsg REPATE_ERROR = new SeckillCodeMsg("A003004", "请不要重复下单");
    public static final SeckillCodeMsg VERIFY_CODE_ERROR = new SeckillCodeMsg("A003005", "验证码输入有误");

    public static final CodeMsg DEFAULT_ERROR = new CodeMsg("B003001","秒殺系统繁忙");
    public static final SeckillCodeMsg OUT_OF_STOCK_ERROR = new SeckillCodeMsg("B003001", "库存不足");
}
