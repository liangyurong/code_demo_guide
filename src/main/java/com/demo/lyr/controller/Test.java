package com.demo.lyr.controller;

public class Test {
    public static void main(String[] args) {
        A a = new A();
        a.update(2);
        a.show();
    }
}

final class A {
     int a = 0;

    public void update(int num) {
        a = num;
        System.out.println(a);
    }


    public void show() {
        System.out.println(a);
    }
}
