package com.demo.lyr.design_patterns.策略模式.鸭子.抽象类具体实现;

import com.demo.lyr.design_patterns.策略模式.鸭子.抽象类.AbstractDuck;
import com.demo.lyr.design_patterns.策略模式.鸭子.策略接口具体实现.FlyBad;
import com.demo.lyr.design_patterns.策略模式.鸭子.策略接口具体实现.SwimBad;

public class ToyDuck extends AbstractDuck {

    public ToyDuck(){
        flyBehavior = new FlyBad();
        swimBehavior = new SwimBad();
    }

    @Override
    public void fly() {
        flyBehavior.fly();
    }

    @Override
    public void swim() {
        swimBehavior.swim();
    }
}

