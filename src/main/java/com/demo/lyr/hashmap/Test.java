package com.demo.lyr.hashmap;

public class Test {
    public static void main(String[] args) {
        int[]a = {1,3,5,7,9};
        int[]b = {2,4,6,8,10};
        System.out.println(calcSumOfMedium(a,b));

    }

    public static long pow(int x, int y) {
        long result = 1;
        while(y!=0) {
            if((y&1) != 0 ){
                result = result * x;
            }
            x = x*x;
            y = y>>1;
        }
        return result;
    }

    public static long calcSumOfMedium(int[] a ,int[] b){
        int m = a.length,  n = b.length;
        if ((m + n)  % 2 == 1)
            return getKthNum(a, b, (m + n + 1) / 2);
        else
            return (long)((getKthNum(a, b, (m + n) / 2) + getKthNum(a, b, (m + n) / 2 + 1)) / 2.0);
    }

    public static long getKthNum(int[] a, int[] b, int k) {
        int m1 = 0, m2 = a.length, n1 = 0, n2 = b.length;
        while(true) {
            if (m1 == m2)
                return b[n1 + k - 1];
            if (n1 == n2)
                return a[m1 + k - 1];
            if (k == 1)
                return Math.min(a[m1], b[n1]);

            int mNextIndex = Math.min(m1 + k / 2 - 1, m2 - 1);
            int nNextIndex = Math.min(n1 + k / 2 - 1, n2 - 1);

            if (a[mNextIndex] <= b[nNextIndex]) {
                k -= mNextIndex - m1 + 1;
                m1 = mNextIndex + 1;
            } else {
                k -= nNextIndex - n1 + 1;
                n1 = nNextIndex + 1;
            }
        }
    }

}
