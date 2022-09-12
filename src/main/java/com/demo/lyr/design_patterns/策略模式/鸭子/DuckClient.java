package com.demo.lyr.design_patterns.策略模式.鸭子;

import com.demo.lyr.design_patterns.策略模式.鸭子.抽象类.Duck;
import com.demo.lyr.design_patterns.策略模式.鸭子.抽象类具体实现.ToyDuck;
import com.demo.lyr.design_patterns.策略模式.鸭子.抽象类具体实现.WildDuck;
import com.demo.lyr.design_patterns.策略模式.鸭子.策略接口具体实现.SwimBad;

public class DuckClient {
    public static void main(String[] args) {
        Duck wildDuck = new WildDuck();
        wildDuck.fly();
        wildDuck.swim();
        wildDuck.setSwimBehavior(new SwimBad()); // 可以随时改变鸭子的行为
        wildDuck.swim();

        Duck toyDuck = new ToyDuck();
        toyDuck.fly();
        toyDuck.swim();

    }
}
