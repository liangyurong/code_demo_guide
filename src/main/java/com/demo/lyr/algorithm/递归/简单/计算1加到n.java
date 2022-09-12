package com.demo.lyr.algorithm.递归.简单;

public class 计算1加到n {

    public static void main(String[] args) {
        System.out.println(getSum(10));
        System.out.println(getSumByRecursion(10));
    }

    /**
     * 迭代
     */
    public static int getSum(int n){
        int result = 0;
        while(n>=0){
            result+=n;
            n--;
        }
        return result;
    }

    /**
     * 递归
     */
    public static int getSumByRecursion(int n){
        // 停止递归条件：n=0
        if(n>0){
            n += getSumByRecursion(n-1);
        }
        return n;
    }

}
