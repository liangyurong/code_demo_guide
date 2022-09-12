package com.demo.lyr.jvm;

import org.springframework.transaction.annotation.Transactional;

public class StringDemo {
    public static void main(String[] args) {
        String str = new String("aaa"); // str直接存储在栈内存中的常量池
        new StringDemo().print(str);
        System.out.println("main方法的str = "+str);
    }

    @Transactional
    public void print(String str){
        str += "bbb";
        System.out.println("print方法的str = "+str);
    }

}

// 运行结果
    //  print方法的str = aaabbb
    //  main方法的str = aaa
