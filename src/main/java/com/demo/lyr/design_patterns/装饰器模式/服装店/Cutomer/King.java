package com.demo.lyr.design_patterns.装饰器模式.服装店.Cutomer;

public class King extends People {

    @Override
    public String show() {
        return "我是女王"+"\n";
    }

    @Override
    public double cost() {
        return 0;
    }
}

