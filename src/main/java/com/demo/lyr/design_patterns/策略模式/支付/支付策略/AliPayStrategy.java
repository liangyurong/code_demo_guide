package com.demo.lyr.design_patterns.策略模式.支付.支付策略;

import com.demo.lyr.design_patterns.策略模式.支付.PaymentStrategy;

public class AliPayStrategy implements PaymentStrategy {
    @Override
    public void buy() {
        System.out.println("阿里支付");
    }
}
