package com.sq.dp.designpattern.observer;

/**
 * 具体观察者1: 进口公司
 */
public class ImportCompany implements Company {
    @Override
    public void response(int number) {
        if (number > 0) {
            System.out.println("人民币汇率升值" + number + "个基点, 降低了进口产品成本, 提升了进口公司利润率.");
        } else if (number < 0) {
            System.out.println("人民币汇率贬值" + (-number) + "个基点, 提升了进口产品成本, 降低了进口公司利润率.");
        }
    }
}
