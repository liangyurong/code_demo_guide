package com.demo.lyr.abstract_demo;

public class AbstractBasic02 {
    public static void main(String[] args) {
        // 抽象类想实例化，必须实现里面的抽象方法
        Animal animal = new Animal(){
            @Override
            public void sleep() {
                System.out.println("睡觉啦");
            }
        };

        animal.sleep(); // 输出“睡觉啦”

    }
}
