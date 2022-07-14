package com.springdemo.springbootrnd.controllers;

import com.springdemo.springbootrnd.models.HealthCheckResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequestMapping("health")
@RestController
@Tag(name = "Health", description = "Basic health check.")
public class HealthCheckController {
    @GetMapping()
    public ResponseEntity<HealthCheckResponse> health() {
        return new ResponseEntity(new HealthCheckResponse(true), HttpStatus.OK);
    }
}
