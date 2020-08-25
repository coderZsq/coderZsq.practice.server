package com.seemygo.shop.cloud.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.seemygo.shop.cloud.alipay.config.AlipayProperties;
import com.seemygo.shop.cloud.domain.OrderInfo;
import com.seemygo.shop.cloud.domain.User;
import com.seemygo.shop.cloud.exception.BusinessException;
import com.seemygo.shop.cloud.service.IOrderInfoService;
import com.seemygo.shop.cloud.util.CookieUtil;
import com.seemygo.shop.cloud.web.SeckillCodeMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RequestMapping("/api/alipay")
@RestController
public class AlipayController extends BaseController {
    @Autowired
    private AlipayProperties properties;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private IOrderInfoService orderInfoService;

    @RequestMapping("/pay")
    public void pay(String orderNo, @CookieValue(CookieUtil.TOKEN_IN_COOKIE) String token, HttpServletResponse resp) throws Exception {
        User user = this.getCurrentUser(token);
        if (user == null) {
            throw new BusinessException(SeckillCodeMsg.OP_ERROR);
        }
        // 发起支付
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(properties.getReturnUrl());
        alipayRequest.setNotifyUrl(properties.getNotifyUrl());

        // 查询订单信息
        OrderInfo order = orderInfoService.findById(orderNo, user.getId());
        if (order == null) {
            throw new BusinessException(SeckillCodeMsg.OP_ERROR);
        }

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orderNo;
        //付款金额，必填
        String total_amount = order.getSeckillPrice() + "";
        //订单名称，必填
        String subject = "秒杀商品: " + order.getGoodName();
        //商品描述，可空
        String body = "";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        // 请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println(result);
        // html: -> <form><script>document.getElementByTag('form')[0].submit
        resp.setContentType("text/html;charset=utf-8");

        // 输出
        resp.getWriter().println(result);
    }

    /**
     * 同步回调, 是在浏览器调用的, 由原先的支付宝支付页面重定向回到该接口
     */
    @RequestMapping("/returnUrl")
    public void returnUrl(@RequestParam Map<String, String> params, HttpServletResponse resp) throws Exception {
        // 支付成功时, 跳转回订单详情页
        boolean signVerified = AlipaySignature.rsaCheckV2(params, properties.getAlipayPublicKey(), properties.getCharset(), properties.getSignType()); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = params.get("out_trade_no");

            //支付宝交易号
            String trade_no = params.get("trade_no");

            //付款金额
            String total_amount = params.get("total_amount");
            // 在用户的浏览器重定向回订单详情页
            resp.sendRedirect("http://localhost/order_detail.html?orderNo=" + out_trade_no);
        }else {
            // 重定向到支付失败的页面
            resp.sendRedirect("http://baidu.com");
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
    }

    /**
     * 异步回调
     * 在同步回调之前执行, 由支付宝后台发起http请求到该接口
     */
    @RequestMapping("/notifyUrl")
    public void notifyUrl() {
        // 支付成功时更新订单状态, 创建发货订单, 为用户添加积分
    }
}
