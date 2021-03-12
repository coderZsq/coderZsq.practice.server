package com.sq.dp.designpattern.bridge;

import java.math.BigDecimal;

/**
 * 普通的方式实现案例
 */
public class PayController {
    public static void main(String[] args) {
        new PayController().pay("001", "24561537863213671532178369164", new BigDecimal("1000.00"), 2, 2);
    }

    public boolean pay(String uId, String tradeId, BigDecimal amount, int channelId, int modeId) {
        if (channelId == 1) {
            // 微信支付
            System.out.println("微信支付: uId=" + uId + ", tradeId=" + tradeId + ", amount=" + amount);
            if (modeId == 1) {
                System.out.println("人脸识别验证中...");
            } else if (modeId == 2) {
                System.out.println("指纹识别验证中...");
            } else if (modeId == 3) {
                System.out.println("密码验证中...");
            }
            System.out.println("-------微信支付成功-------");
        } else if (channelId == 2) {
            // 支付宝支付
            System.out.println("支付宝支付: uId=" + uId + ", tradeId=" + tradeId + ", amount=" + amount);
            if (modeId == 1) {
                System.out.println("人脸识别验证中...");
            } else if (modeId == 2) {
                System.out.println("指纹识别验证中...");
            } else if (modeId == 3) {
                System.out.println("密码验证中...");
            }
            System.out.println("-------支付宝支付成功-------");
        }
        return true;
    }
}
