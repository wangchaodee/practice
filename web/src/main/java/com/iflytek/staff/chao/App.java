package com.iflytek.staff.chao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Hello world!
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        SpringApplication.run(App.class);


    }
}
