package com.systen.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthBackendApplication.class, args);
        System.out.println("Servidor rodando");
    }
}
