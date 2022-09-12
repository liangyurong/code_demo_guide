package com.demo.lyr.多线程.三个串口同时卖100张票;

// 线程AB交替输出1-100 （A1B2A3B4A5B6）
public class Test {
    public static void main(String[] args) {
        Task task = new Task();
        Thread a = new Thread(task, "线程A");
        Thread b = new Thread(task, "线程B");
        Thread c = new Thread(task, "线程C");

        a.start();
        b.start();
        c.start();

    }

}

class Task implements Runnable{

    private Object lock = new Object();

    private int i =1 ;

    @Override
    public void run() {
        synchronized (lock){
            while (i <= 100) {
                System.out.println(Thread.currentThread().getName()+"  i==" + i);
                i++;            // i++的位置很重要
                lock.notify();  // 随机唤醒正在等待的线程
                if(i>=100){
                    break;
                }
                try {
                    lock.wait(); // 当前线程进入等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}


