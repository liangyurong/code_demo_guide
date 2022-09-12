package com.demo.lyr.多线程.创建线程;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
        //
        Callable<String> callable = () -> {
            String msg = Thread.currentThread().getName() + ": 在执行...";
            return msg;
        };
        // 异步任务对象
        FutureTask<String> task =new FutureTask<>(callable);
        // 线程对象
        Thread t = new Thread(task);
        t.setName("线程1");
        t.start();

        // 获取线程执行的返回值
        try {
            System.out.println(task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
