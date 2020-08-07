package com.seemygo.shop.cloud.web.advice.msg;


import com.seemygo.shop.cloud.resp.CodeMsg;

public class GoodsCodeMsg extends CodeMsg {
    private GoodsCodeMsg(String code, String msg){
        super(code,msg);
    }
    public static final GoodsCodeMsg DEFAULT_ERROR = new GoodsCodeMsg("B002001","商品服务繁忙");

}
