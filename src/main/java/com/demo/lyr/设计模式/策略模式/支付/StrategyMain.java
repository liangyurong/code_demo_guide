package com.demo.lyr.设计模式.策略模式.支付;

public class StrategyMain {
    public static void main(String[] args) {

        int type1 = 1 ; // 此处表示前端传进来的值
        PaymentType paymentType1 = PaymentType.getPaymentType(type1);
        PaymentStrategy payment1 = PaymentContext.getGoodsPayByType(paymentType1);
        payment1.buy();

        int type2 = 2 ; // 此处表示前端传进来的值
        PaymentType paymentType2 = PaymentType.getPaymentType(type2);
        PaymentStrategy payment2 = PaymentContext.getGoodsPayByType(paymentType2);
        payment2.buy();

    }
}
