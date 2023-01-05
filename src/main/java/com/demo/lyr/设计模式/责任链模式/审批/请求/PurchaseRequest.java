package com.demo.lyr.设计模式.责任链模式.审批.请求;

/**
 * 购买请求
 */
public class PurchaseRequest {

    // 请求金额
    private double price = 0;

    public PurchaseRequest(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
