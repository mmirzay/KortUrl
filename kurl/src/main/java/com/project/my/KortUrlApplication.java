package com.project.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KortUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(KortUrlApplication.class, args);
        System.out.println("Simple URL Shorter");
    }

}
