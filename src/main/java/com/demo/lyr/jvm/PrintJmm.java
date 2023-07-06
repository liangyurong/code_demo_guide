package com.demo.lyr.jvm;

import org.openjdk.jol.info.ClassLayout;

public class PrintJmm {
    public static void main(String[] args) throws InterruptedException {
        Man man = new Man();

        //输出 对象 的布局
        System.out.println(ClassLayout.parseInstance(man).toPrintable());

    }
}

class Man{
    private boolean flag = true;
}