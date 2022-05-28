package com.springdemo.springbootrnd.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("health")
@RestController
public class HealthCheckController {
    @GetMapping()
    public HashMap<String,String> getPersonById() {
        return new HashMap<>() {{
            put("healthCheck","success");
        }};
    }
}
