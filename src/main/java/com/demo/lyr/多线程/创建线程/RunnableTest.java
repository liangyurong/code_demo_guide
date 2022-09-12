package com.demo.lyr.多线程.创建线程;

public class RunnableTest {
    public static void main(String[] args) {
        // 任务
        Runnable task = () -> System.out.println(Thread.currentThread().getName());
        // 线程对象
        Thread t  = new Thread(task);
        // 启动线程
        t.start();
    }
}
