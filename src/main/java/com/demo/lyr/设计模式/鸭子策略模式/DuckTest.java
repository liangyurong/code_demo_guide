package com.demo.lyr.设计模式.鸭子策略模式;

import com.demo.lyr.设计模式.鸭子策略模式.鸭子.Duck;
import com.demo.lyr.设计模式.鸭子策略模式.鸭子.RedHeadDuck;

public class DuckTest {
    public static void main(String[] args) {
        Duck redHeadDuck = new RedHeadDuck();
        redHeadDuck.performFly();
        redHeadDuck.performQuack();
        redHeadDuck.display();
    }
}
