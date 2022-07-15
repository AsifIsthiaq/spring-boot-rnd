package com.springdemo.springbootrnd;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot API", version = "1.0.1", description = "This is a Demo Application built using Spring Boot & Postgres."))
@EnableCaching
public class SpringBootRndApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRndApplication.class, args);
    }

}

/**
 * private final Logger LOG = LoggerFactory.getLogger(getClass());
 */