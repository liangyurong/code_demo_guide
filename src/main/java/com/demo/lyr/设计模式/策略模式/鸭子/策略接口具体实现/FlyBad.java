package com.demo.lyr.设计模式.策略模式.鸭子.策略接口具体实现;

import com.demo.lyr.设计模式.策略模式.鸭子.策略接口.FlyBehavior;

public class FlyBad implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly bad");
    }
}
