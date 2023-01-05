package com.demo.lyr.设计模式.装饰器模式.服装店;

import com.demo.lyr.设计模式.装饰器模式.服装店.Clothes.Pants;
import com.demo.lyr.设计模式.装饰器模式.服装店.Clothes.Shirt;
import com.demo.lyr.设计模式.装饰器模式.服装店.Cutomer.King;
import com.demo.lyr.设计模式.装饰器模式.服装店.Cutomer.People;
import com.demo.lyr.设计模式.装饰器模式.服装店.Cutomer.Student;

public class ClothesMain {
    public static void main(String[] args) {
        People student = new Student();
        student = new Pants(student);
        student = new Shirt(student);
        student = new Shirt(student);
        System.out.println(student.show());
        System.out.println("总共花了："+student.cost());

        People king = new King();
        king = new Pants(king);
        king = new Shirt(king);
        king = new Shirt(king);
        king = new Shirt(king);
        System.out.println(king.show());
        System.out.println("总共花了："+king.cost());

    }
}
