package com.sq.dp.designpattern.factory.methodfactory;

public class PayController {

    private AbstractPayFactory payFactory;

    public void setPayFactory(AbstractPayFactory payFactory) {
        this.payFactory = payFactory;
    }

    public static void main(String[] args) {
        // 阿里web支付
        PayController payController = new PayController();
        payController.setPayFactory(new BankPayFactory());
        payController.pay();
    }

    /**
     * 消费者
     */
    public void pay() {
        Pay pay = payFactory.createPay();
        pay.build();
        pay.signature();
        pay.verifySignature();
        pay.payment();
    }
}
