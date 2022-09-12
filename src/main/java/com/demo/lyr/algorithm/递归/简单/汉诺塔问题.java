package com.demo.lyr.algorithm.递归.简单;

/**
 * 汉诺塔问题
 * 1、移动原则：碟子总是小的在大的上面 ， 每次只能移动一个碟子
 * 2、n代表左边碟子的数量
 * 3、按照移动原则，将碟子移动到最右边
 */
public class 汉诺塔问题 {

    public static void main(String[] args) {
        hanoi(3,"A","B","C");
    }

    /**
     * @param n 最左边碟子的个数
     * 关键： (n-1)看作一个整体
     */
    public static void hanoi(int n,String A,String B,String C){
        if (n==0){
            return;
        }else if(n==1){
            move(A,C);
        }else {
            hanoi(n-1,A,C,B);            // (n-1)整体 -> move(A,B)
            move(A,C);                      // n -> move(A,C)
            hanoi(n-1,B,A,C);            // (n-1)整体 -> move(B,C)
        }
    }

    public static void move(String a,String b){
        System.out.println(a+" -> "+b);
    }

}
