package com.springdemo.springbootrnd;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Persons API", version = "1.0", description = "Persons Information"))
public class SpringBootRndApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRndApplication.class, args);
    }

}
