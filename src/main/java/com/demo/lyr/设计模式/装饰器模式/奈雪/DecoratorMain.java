package com.demo.lyr.设计模式.装饰器模式.奈雪;


import com.demo.lyr.设计模式.装饰器模式.奈雪.配料.Bobo;
import com.demo.lyr.设计模式.装饰器模式.奈雪.配料.Pudding;
import com.demo.lyr.设计模式.装饰器模式.奈雪.饮料.Beverage;
import com.demo.lyr.设计模式.装饰器模式.奈雪.饮料.Coffee;
import com.demo.lyr.设计模式.装饰器模式.奈雪.饮料.MilkTea;

public class DecoratorMain {
    public static void main(String[] args) {
        // 奶茶 (加多少配料都是奶茶)
        Beverage milkTea = new MilkTea();
        milkTea = new Bobo(milkTea);
        milkTea = new Pudding(milkTea);
        double cost = milkTea.cost();
        System.out.println("结账："+cost);

        // 咖啡 (加多少配料都是咖啡)
        Beverage coffee = new Coffee();
        coffee = new Pudding(coffee);
        double cost1 = coffee.cost();
        System.out.println("结账："+cost1);

    }
}
