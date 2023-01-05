package com.demo.lyr.设计模式.策略模式.鸭子.策略接口具体实现;

import com.demo.lyr.设计模式.策略模式.鸭子.策略接口.SwimBehavior;

public class SwimBad implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("swim bad");
    }
}
