package com.demo.lyr.design_patterns.策略模式.鸭子.抽象类具体实现;

import com.demo.lyr.design_patterns.策略模式.鸭子.抽象类.AbstractDuck;
import com.demo.lyr.design_patterns.策略模式.鸭子.策略接口具体实现.FlyGood;
import com.demo.lyr.design_patterns.策略模式.鸭子.策略接口具体实现.SwimGood;

public class WildDuck extends AbstractDuck {

    public WildDuck(){
        this.flyBehavior = new FlyGood();
        this.swimBehavior = new SwimGood();
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
