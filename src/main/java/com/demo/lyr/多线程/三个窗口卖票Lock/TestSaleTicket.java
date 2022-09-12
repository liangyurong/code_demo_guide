package com.demo.lyr.多线程.三个窗口卖票Lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestSaleTicket {
    public static void main(String[] args) {
        Task task = new Task();

        // 三条线程相当于三个窗口
        Thread t1 = new Thread(task,"窗口1");
        Thread t2 = new Thread(task,"窗口2");
        Thread t3 = new Thread(task,"窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Task implements Runnable{

    private ReentrantLock lock = new ReentrantLock();

    private int ticket = 0 ;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            if(ticket<=99){
                ticket++;  // ++的位置很重要
                System.out.println(Thread.currentThread().getName()+"  i==" + ticket);
            }else {
                lock.unlock();
                break;
            }
            lock.unlock();

        }
    }
}