package com.springdemo.springbootrnd.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("health")
@RestController
@Tag(name = "Health", description = "Basic health check.")
public class HealthCheckController {
    @GetMapping()
    public HashMap<String,String> getPersonById() {
        return new HashMap<>() {{
            put("healthCheck","success");
        }};
    }
}
