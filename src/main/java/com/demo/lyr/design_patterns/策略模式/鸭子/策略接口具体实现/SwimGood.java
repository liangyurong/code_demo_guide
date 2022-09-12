package com.demo.lyr.design_patterns.策略模式.鸭子.策略接口具体实现;

import com.demo.lyr.design_patterns.策略模式.鸭子.策略接口.SwimBehavior;

public class SwimGood implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("swim good");
    }
}
