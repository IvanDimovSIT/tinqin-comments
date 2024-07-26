package com.tinqinacademy.comments.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.tinqinacademy")
@EnableJpaRepositories(basePackages = "com.tinqinacademy.comments.persistence")
@EntityScan(basePackages = "com.tinqinacademy.comments.persistence.model")
public class CommentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentsApplication.class, args);
    }

}

