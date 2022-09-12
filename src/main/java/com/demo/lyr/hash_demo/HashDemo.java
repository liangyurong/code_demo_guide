package com.demo.lyr.hash_demo;

// https://www.bilibili.com/video/BV1E4411H73v?p=88

public class HashDemo {

    public static void main(String[] args) {

    }


}

 class EmpList {

    // 头指针，直接指向第一个Emp
    private Emp head ;

    // 添加Emp到链表
    public void add(Emp emp){
        if(head==null){
            head=emp;
        }
        Emp cur = head;
        while (true){
            if(cur.next == null){
                break;
            }
            cur = cur.next;
        }
        // 退出时直接将tmp加入链表末尾
        cur.next = emp;
    }

}

 class Emp {

    // 假设id自增
    public int id;

    public String name;

    // 指向下一个user
    public Emp next;

    public Emp(int id,String name){
        this.id = id;
        this.name = name;
    }
 }
