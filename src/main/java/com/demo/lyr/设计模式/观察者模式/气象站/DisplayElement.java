package com.demo.lyr.设计模式.观察者模式.气象站;

/**
 * 显示板接口
 */
public interface DisplayElement {

    /**
     * 显示布告板信息
     * 备注：当布告板需要显示时，调用此方法
     */
    void display();
}
