package com.demo.lyr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunApp {

    public static void main(String[] args) {
        try {
            SpringApplication.run(RunApp.class,args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
