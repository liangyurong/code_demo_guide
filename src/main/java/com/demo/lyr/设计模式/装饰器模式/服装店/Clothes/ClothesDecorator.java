package com.demo.lyr.设计模式.装饰器模式.服装店.Clothes;

import com.demo.lyr.设计模式.装饰器模式.服装店.Cutomer.People;

public abstract class ClothesDecorator extends People {
   public abstract String show();
   public abstract double cost();
}
