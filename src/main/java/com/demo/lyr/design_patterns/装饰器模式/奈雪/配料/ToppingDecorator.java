package com.demo.lyr.design_patterns.装饰器模式.奈雪.配料;

import com.demo.lyr.design_patterns.装饰器模式.奈雪.饮料.Beverage;

/**
 * 配料-装饰器
 */
public abstract class ToppingDecorator extends Beverage {
    public abstract double cost();
}
