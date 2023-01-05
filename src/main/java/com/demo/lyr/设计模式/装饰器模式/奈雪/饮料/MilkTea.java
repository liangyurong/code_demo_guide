package com.demo.lyr.设计模式.装饰器模式.奈雪.饮料;

public class MilkTea extends Beverage {


    @Override
    public double cost() {
        System.out.println("奶茶：5元");
        return 5.00;
    }
}
