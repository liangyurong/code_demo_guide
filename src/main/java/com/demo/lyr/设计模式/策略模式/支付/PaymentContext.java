package com.demo.lyr.设计模式.策略模式.支付;

import com.demo.lyr.设计模式.策略模式.支付.支付策略.AliPayStrategy;
import com.demo.lyr.设计模式.策略模式.支付.支付策略.BankCardStrategy;
import com.demo.lyr.设计模式.策略模式.支付.支付策略.WeChatStrategy;
import com.demo.lyr.设计模式.策略模式.支付.支付策略.WhiteStrategy;

/**
 * 商品支付
 */
public class PaymentContext {

    private PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    // 新增一种支付策略，则必须在switch中添加代码
    public static PaymentStrategy getGoodsPayByType(PaymentType type){
        PaymentStrategy paymentStrategy = null;
        switch (type){
            case ALIPAY:
                paymentStrategy = new AliPayStrategy();
                break;
            case WEIXIN:
                paymentStrategy = new WeChatStrategy();
                break;
            case BANKCARD:
                paymentStrategy = new BankCardStrategy();
                break;
            case WHITE:
                paymentStrategy = new WhiteStrategy();
                break;
            default:
                break;
        }
        return paymentStrategy;
    }

}
