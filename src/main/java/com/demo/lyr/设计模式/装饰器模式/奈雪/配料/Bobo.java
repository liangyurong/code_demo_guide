package com.demo.lyr.设计模式.装饰器模式.奈雪.配料;

import com.demo.lyr.设计模式.装饰器模式.奈雪.饮料.Beverage;

/**
 * 啵啵
 */
public class Bobo extends ToppingDecorator {

    private static final int COST = 1;

    private final Beverage beverage;

    public Bobo(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        System.out.println("加一份啵啵: 1元");
        return COST+beverage.cost();
    }
}
