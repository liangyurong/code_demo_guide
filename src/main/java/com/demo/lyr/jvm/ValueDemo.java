package com.demo.lyr.jvm;

/**
 *  在运行main方法时，会创立一个独立的栈帧，里面有个变量a=10
 *
 *  在运行print方法，会创建一个独立的栈帧，a的初始值是10，然后a=20。在print方法执行完，则该栈帧被销毁
 *
 *  执行到System.out.println("main方法里面的a ： " + a)，实际输出的是main栈帧的a，因此是10
 */
public class ValueDemo {
    public static void main(String[] args) {
        int a = 10;
        print(a);                                    // a=20
        System.out.println("main方法里面的a ： " + a); // a=10
    }

    public static void print(int a){
        a = 20;
        System.out.println("print方法里面的a ： " + a);
    }

}
