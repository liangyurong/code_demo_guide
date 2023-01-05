package com.demo.lyr.设计模式.装饰器模式.服装店.Cutomer;

public class Student extends People{

    @Override
    public String show() {
        return "我是小学生"+"\n";
    }

    @Override
    public double cost() {
        return 0;
    }
}
