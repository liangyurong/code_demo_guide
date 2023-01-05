package com.demo.lyr.设计模式.鸭子策略模式.鸭子;

import com.demo.lyr.设计模式.鸭子策略模式.飞行.FlyWithSWings;
import com.demo.lyr.设计模式.鸭子策略模式.鸭子叫.QuackLoud;

public class RedHeadDuck extends Duck {

    public RedHeadDuck(){
        quackBehavior = new QuackLoud();
        flyBehavior = new FlyWithSWings();
    }

    @Override
    public void display() {
        System.out.println("我是红鸭子");
    }
}
