package com.demo.lyr.设计模式.鸭子策略模式.飞行;

public class FlyWithSWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("鸭仔在飞");
    }
}
