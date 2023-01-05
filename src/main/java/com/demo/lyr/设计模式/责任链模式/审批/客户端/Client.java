package com.demo.lyr.设计模式.责任链模式.审批.客户端;

import com.demo.lyr.设计模式.责任链模式.审批.具体的Handler.AHandler;
import com.demo.lyr.设计模式.责任链模式.审批.具体的Handler.BHandler;
import com.demo.lyr.设计模式.责任链模式.审批.具体的Handler.CHandler;
import com.demo.lyr.设计模式.责任链模式.审批.请求.PurchaseRequest;

public class Client {
    public static void main(String[] args) {
        PurchaseRequest request = new PurchaseRequest(-52000);

        AHandler a = new AHandler();
        BHandler b = new BHandler();
        CHandler c = new CHandler();

        a.setNext(b);
        b.setNext(c);

        a.handleDealRequest(request);



    }
}
