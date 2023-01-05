package com.demo.lyr.设计模式.鸭子策略模式.鸭子;

import com.demo.lyr.设计模式.鸭子策略模式.飞行.FlyNoWay;
import com.demo.lyr.设计模式.鸭子策略模式.鸭子叫.QuackSilence;

public class ToyDuck extends Duck {

    public ToyDuck(){
        quackBehavior = new QuackSilence();
        flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("我是玩具鸭子");
    }
}
