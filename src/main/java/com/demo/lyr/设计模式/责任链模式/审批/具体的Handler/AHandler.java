package com.demo.lyr.设计模式.责任链模式.审批.具体的Handler;

import com.demo.lyr.设计模式.责任链模式.审批.请求.PurchaseRequest;

public class AHandler extends Handler{

    @Override
    public void handleDealRequest(PurchaseRequest request) {
        if(request.getPrice()>0 && request.getPrice()<=5000){
            System.out.println("A已经处理了审批请求");
        }else {
            System.out.println("金额超限，A处理不了，已提交下一级处理");
            letNextHandlerSolve(request);
        }
    }
}
