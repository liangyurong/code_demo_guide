package com.demo.lyr.设计模式.装饰器模式.服装店.Clothes;

import com.demo.lyr.设计模式.装饰器模式.服装店.Cutomer.People;

public class Shirt extends ClothesDecorator {

    private final double COST = 20.00;
    private final String SHOW = "买了一条衬衫"+"\n";

    private People people;

    public Shirt(People people){
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
