package com.demo.lyr.设计模式.策略模式.支付;

/**
 * 支付类型：阿里支付、微信支付、银行卡支付、白条支付
 */
public enum PaymentType {

    ALIPAY(1),
    WEIXIN(2),
    BANKCARD(3),
    WHITE(4);

    // 前端传递的支付类型的int值
    private int type;

    public int getType() {
        return type;
    }

    PaymentType(int type) {
        this.type = type;
    }

    public static PaymentType getPaymentType(int type) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.getType() == type) {
                return paymentType;
            }
        }
        return null;
    }


}
