package com.demo.lyr.design_patterns.装饰器模式.服装店.Clothes;

import com.demo.lyr.design_patterns.装饰器模式.服装店.Cutomer.People;

public abstract class ClothesDecorator extends People {
   public abstract String show();
   public abstract double cost();
}
