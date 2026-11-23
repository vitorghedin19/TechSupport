package com.example.techsupport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Ponto de entrada do back (Spring Boot).
@SpringBootApplication
public class TechSupportApplication {

    public static void main(String[] args) {
        System.out.println("HelloWorld");
        SpringApplication.run(TechSupportApplication.class, args);
    }
}
