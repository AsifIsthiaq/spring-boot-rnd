package com.springdemo.springbootrnd.controllers;

import com.springdemo.springbootrnd.models.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("health")
@RestController
public class HealthCheckController {
    @GetMapping()
    public HashMap<String,String> getPersonById() {
        return new HashMap<String,String>() {{
            put("healthCheck","success");
        }};
    }
}
