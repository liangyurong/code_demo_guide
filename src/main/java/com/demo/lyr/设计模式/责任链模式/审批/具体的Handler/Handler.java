package com.demo.lyr.设计模式.责任链模式.审批.具体的Handler;

import com.demo.lyr.设计模式.责任链模式.审批.请求.PurchaseRequest;

// 抽象的处理者，定义了一个处理请求的接口，同时含有另一个handler
public abstract class Handler {

    // 下一个处理者
    private Handler next;

    public Handler() {

    }

    // 设置下一个处理者
    public void setNext(Handler next) {
        this.next = next;
    }

    // 自己处理负责的事情
    abstract void handleDealRequest(PurchaseRequest request);

    // 传递给下一个handler去处理
    protected final void letNextHandlerSolve(PurchaseRequest request) {
        if (next != null) {
            this.next.handleDealRequest(request);
        }else {
            System.out.println("处理失败");
        }
    }

}
