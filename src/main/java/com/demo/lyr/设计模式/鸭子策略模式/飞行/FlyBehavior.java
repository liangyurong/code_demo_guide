package com.demo.lyr.设计模式.鸭子策略模式.飞行;

/**
 * 能飞的鸭子实现该接口
 */
public interface FlyBehavior {

    /**
     * 不可以在Duck直接加上fly()方法，因为不适所有鸭子都会飞
     */
    void fly();

}
