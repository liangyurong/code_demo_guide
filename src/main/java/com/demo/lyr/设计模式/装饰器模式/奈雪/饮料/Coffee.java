package com.demo.lyr.设计模式.装饰器模式.奈雪.饮料;

public class Coffee extends Beverage {
    @Override
    public double cost() {
        System.out.println("咖啡10元");
        return 10.0;
    }
}
