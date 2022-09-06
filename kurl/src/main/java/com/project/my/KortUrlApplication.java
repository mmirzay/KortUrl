package com.project.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KortUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(KortUrlApplication.class, args);
        System.out.println("Simple URL Shorter");
    }

}
