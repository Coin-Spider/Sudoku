package com.example.no0001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class No0001Application {
    public static void main(String[] args) {

        SpringApplication.run(No0001Application.class, args);
    }

}
