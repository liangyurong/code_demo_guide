package com.demo.lyr.jvm;

/**
 *  print(p)方法执行完，对应的栈帧结构已经销毁，为什么还是（2,"name2"）?
 *      因为对象p是放在堆里面的，print方法的栈帧结构存放的只是p的内存地址，栈帧销毁不影响堆内存
 */
public class ClassDemo {
    public static void main(String[] args) {
        Person p = new Person();
        p.setId(1);
        p.setName("name1");
        System.out.println(p.toString()); // 运行print方法前
        new ClassDemo().print(p);
        System.out.println(p.toString()); // 运行print方法后
    }

    public  void print(Person p){
        p.setId(2);
        p.setName("name2");
    }

}


class Person{
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getId()+" : "+this.getName();
    }
}
