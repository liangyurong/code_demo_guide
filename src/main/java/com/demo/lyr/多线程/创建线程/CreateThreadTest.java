package com.demo.lyr.多线程.创建线程;

public class CreateThreadTest {

    public static void main(String[] args) throws Exception {

        Room room = new Room();

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                room.decrement();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.increment();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        room.print();

    }
}

class Room{
    private int a = 0;

    public void increment(){
        synchronized (this){
            a++;
            System.out.println(Thread.currentThread().getName()+" : a = "+a);
        }
    }

    public void decrement(){
        synchronized (this){
            a--;
            System.out.println(Thread.currentThread().getName()+" : a = "+a);
        }
    }

    public void print(){
        System.out.println("a = "+a);
    }

}

