package com.springdemo.springbootrnd;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@OpenAPIDefinition(info = @Info(title = "Spring Boot API", description = "This is a Demo Application built using Spring Boot, Postgres & Redis."))
@EnableCaching
//@ComponentScan(basePackages = "com.springdemo.springbootrnd.services")
public class SpringBootRndApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRndApplication.class, args);
    }
}

/**
 * private final Logger LOG = LoggerFactory.getLogger(getClass());
 */