package com.demo.lyr.设计模式.装饰器模式.奈雪.配料;

import com.demo.lyr.设计模式.装饰器模式.奈雪.饮料.Beverage;

/**
 * 布丁
 */
public class Pudding extends ToppingDecorator {

    private static final int COST = 5;

    private final Beverage beverage;

    public Pudding(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        System.out.println("加一份布丁: 5元");
        return COST+beverage.cost();
    }
}
