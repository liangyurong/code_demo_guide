package com.demo.lyr.多线程.创建线程;

// 结果分析：明显看出线程1输出的数字比线程2要多得多
public class ThreadTest {
    public static void main(String[] args)  {
        // 创建线程1
        Thread t1 = new Thread(()-> {
            int count = 1 ;
            while (true){
                System.out.println("--->1 "+count++);
            }
        });

        // 创建线程2
        Thread t2 = new Thread(()-> {
            int count = 1 ;
            while (true){
                Thread.yield(); // 让出线程2对cpu的使用
                System.out.println("      --->2 "+count++);
            }
        });

        t1.start();
        t2.start();

    }

}
