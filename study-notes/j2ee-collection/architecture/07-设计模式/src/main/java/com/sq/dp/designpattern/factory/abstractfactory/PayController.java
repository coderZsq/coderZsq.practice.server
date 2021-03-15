package com.sq.dp.designpattern.factory.abstractfactory;

/**
 * 支付的消费方
 */
public class PayController {
    /**
     * 依赖抽象工厂
     */
    private AbstractCloudApiFactory apiFactory;

    /**
     * 注入对应的子类工厂
     * @param apiFactory
     */
    public void setApiFactory(AbstractCloudApiFactory apiFactory) {
        this.apiFactory = apiFactory;
    }
    public void pay() {
        Pay pay = apiFactory.createPay();
        pay.build();
        pay.signature();
        pay.verifySignature();
        pay.payment();
    }
}
