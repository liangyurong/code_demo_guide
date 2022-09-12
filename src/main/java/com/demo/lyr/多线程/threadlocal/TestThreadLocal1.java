package com.demo.lyr.多线程.threadlocal;

public class TestThreadLocal1 {
    public static void main(String[] args){

        TestThreadLocal1 en = new TestThreadLocal1();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                synchronized (TestThreadLocal1.class){
                    en.setContent(Thread.currentThread().getName()+"的数据");
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"---> "+en.getContent());
                }
            }).start();
        }
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;




}
