package com.springdemo.springbootrnd.models;

public class HealthCheckResponse {
    private Boolean success;

    public HealthCheckResponse(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }
}
