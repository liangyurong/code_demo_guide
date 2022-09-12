package com.demo.lyr.design_patterns.策略模式.支付.支付策略;

import com.demo.lyr.design_patterns.策略模式.支付.PaymentStrategy;

public class BankCardStrategy implements PaymentStrategy {
    @Override
    public void buy() {
        System.out.println("银行卡支付");
    }
}
