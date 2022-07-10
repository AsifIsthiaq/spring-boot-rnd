package com.springdemo.springbootrnd;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot API", version = "1.0.1", description = "This is a Demo Application built using Spring Boot & Postgres."))
public class SpringBootRndApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRndApplication.class, args);
    }

}
