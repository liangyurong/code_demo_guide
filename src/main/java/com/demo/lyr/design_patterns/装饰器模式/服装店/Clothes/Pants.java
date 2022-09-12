package com.demo.lyr.design_patterns.装饰器模式.服装店.Clothes;

import com.demo.lyr.design_patterns.装饰器模式.服装店.Cutomer.People;

public class Pants extends ClothesDecorator {

    private final double COST = 10.00;
    private final String SHOW = "买了一条裤子"+"\n";

    private People people;

    public Pants(People people){
        this.people = people;
    }

    @Override
    public String show() {
       return people.show()+SHOW;
    }

    @Override
    public double cost() {
        return people.cost()+COST;
    }
}
