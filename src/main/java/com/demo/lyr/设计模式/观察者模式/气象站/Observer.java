package com.demo.lyr.设计模式.观察者模式.气象站;

/**
 * 观察者接口
 */
public interface Observer {

    /**
     * 更新方法
     * 备注：当气象观测值发生变化，主题会把这些状态值当作方法的参数，传递给观察者
     * @param temp 温度
     * @param humidity 湿度
     * @param pressure 气压
     */
    void update(float temp,float humidity,float pressure);

}
