package com.springdemo.springbootrnd.controllers;

import com.springdemo.springbootrnd.models.HealthCheckResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("health")
@RestController
@Tag(name = "Health", description = "Basic health check.")
public class HealthCheckController {
    @Value("${app.version}")
    private String version;
    @Value("${app.java-version}")
    private String javaVersion;

    @GetMapping("/status")
    public ResponseEntity<HealthCheckResponse> health() {
        return new ResponseEntity(new HealthCheckResponse(version,javaVersion), HttpStatus.OK);
    }
}
