package com.springdemo.springbootrnd.models;

public class HealthCheckResponse {
    private String status = "Running";
    private String version;
    private String javaVersion;

    public HealthCheckResponse(String version, String javaVersion) {
        this.version = version;
        this.javaVersion = javaVersion;
        System.out.println("api-version: " + version);
    }

    public String getStatus() {
        return status;
    }

    public String getVersion() {
        return version;
    }

    public String getJavaVersion() {
        return javaVersion;
    }
}
