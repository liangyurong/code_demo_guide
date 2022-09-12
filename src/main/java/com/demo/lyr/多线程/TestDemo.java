package com.demo.lyr.多线程;

public class TestDemo {

    // 告诉线程，每次读取需要从主内存中读取，不能从自身的缓存中读取
    static volatile boolean flag = false;

    public static void main(String[] args) throws Exception {

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"执行");
            while (!flag){

            }
            System.out.println("当flag为true，"+Thread.currentThread().getName()+"执行return");
            return;
        }).start();

        Thread.sleep(10);
        flag = true;
        System.out.println("main线程将flag改为true");

    }
}





