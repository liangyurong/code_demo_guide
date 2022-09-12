package com.demo.lyr.design_patterns.责任链模式.审批.具体的Handler;

import com.demo.lyr.design_patterns.责任链模式.审批.请求.PurchaseRequest;

public class CHandler extends Handler{

    @Override
    public void handleDealRequest(PurchaseRequest request) {
        if(request.getPrice()>10000 && request.getPrice()<=20000){
            System.out.println("C已经处理了审批请求");
        }else {
            System.out.println("金额超限，C处理不了，已提交下一级处理");
            letNextHandlerSolve(request);
        }
    }
}

