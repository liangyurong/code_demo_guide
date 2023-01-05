package com.demo.lyr.设计模式.鸭子策略模式.鸭子;

import com.demo.lyr.设计模式.鸭子策略模式.飞行.FlyBehavior;
import com.demo.lyr.设计模式.鸭子策略模式.鸭子叫.QuackBehavior;

public abstract class Duck {

    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck(){
    }

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }

    /**
     * 游泳
     */
    public void swim(){
        System.out.println("每个鸭子都会游泳");
    }

    /**
     * 外观
     */
    public abstract void display();

}
