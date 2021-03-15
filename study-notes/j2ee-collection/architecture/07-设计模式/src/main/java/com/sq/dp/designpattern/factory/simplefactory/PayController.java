package com.sq.dp.designpattern.factory.simplefactory;

public class PayController {

    private PaySimpleFactory simpleFactory;

    public void setSimpleFactory(PaySimpleFactory simpleFactory) {
        this.simpleFactory = simpleFactory;
    }

    public static void main(String[] args) {
        // 模拟支付
        PayController payController = new PayController();
        payController.setSimpleFactory(new PaySimpleFactory());
        payController.pay("ali");
    }

    /**
     * 消费者
     */
    public void pay(String payType) {
        Pay pay = simpleFactory.createPay(payType);
        pay.build();
        pay.signature();
        pay.verifySignature();
        pay.payment();
    }

    // public void pay() {
    //     Pay pay = null;
    //     String payType = getType();
    //
    //     switch (payType) {
    //         case "ali":
    //             pay = new AliPay();
    //             pay.setName("支付宝支付");
    //             break;
    //         case "wechat":
    //             pay = new WechatPay();
    //             pay.setName("微信支付");
    //             break;
    //         default:
    //             System.out.println("支付类型不支持");
    //             break;
    //     }
    //
    //     pay.build();
    //     pay.signature();
    //     pay.verifySignature();
    //     pay.payment();
    // }

    // private String getType() {
    //     try {
    //         BufferedReader strIn = new BufferedReader(new InputStreamReader(System.in));
    //         System.out.println("请输入支付类型");
    //         return strIn.readLine();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return "";
    //     }
    // }
}
