package com.tinqinacademy.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tinqinacademy")
public class CommentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication.class, args);
    }

}

