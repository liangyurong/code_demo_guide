package com.demo.lyr.abstract_demo;

public class AbstractBasic01 {

    public static void main(String[] args) {
        Animal duck = new Duck();
        duck.sleep();
    }

}

class Duck extends Animal{
    @Override
    public void sleep() {
        System.out.println("睡觉啦");
    }
}

abstract class Animal{
    abstract public void sleep();
}


