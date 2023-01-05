package com.demo.lyr.设计模式.观察者模式.气象站;

/**
 * 主题接口
 */
public interface Subject {

    /**
     * 注册观察者
     */
    void registerObserver(Observer observer);
    /**
     * 删除观察者
     */
    void removeObserver(Observer observer);
    /**
     * 通知所有观察者
     * 备注：主题状态改变，这个方法会被调用，以通知所有的观察者
     */
    void notifyObservers();

}
