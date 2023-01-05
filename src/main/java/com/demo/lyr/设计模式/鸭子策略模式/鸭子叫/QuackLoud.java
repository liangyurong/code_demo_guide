package com.demo.lyr.设计模式.鸭子策略模式.鸭子叫;

public class QuackLoud implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("鸭子大声叫");
    }
}
