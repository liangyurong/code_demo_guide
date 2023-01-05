package com.demo.lyr.设计模式.装饰器模式.奈雪.饮料;

public class FruitTea extends Beverage {
    @Override
    public double cost() {
        System.out.println("水果茶8元");
        return 8.00;
    }
}
