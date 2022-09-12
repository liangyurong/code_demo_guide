package com.demo.lyr.jvm;

/**
 * 数组也是放在堆里面的。
 * 方法的栈帧销毁，不影响堆的数据
 */
public class ArrDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3};
        new ArrDemo().print(arr);                              // print方法里面的arr[0] ： 10
        System.out.println("main方法里面的arr[0] ： " + arr[0]); // main方法里面的arr[0] ： 10
    }

    public void print(int[] arr){
        arr[0] = 10;
        System.out.println("print方法里面的arr[0] ： " + arr[0]);
    }
}
