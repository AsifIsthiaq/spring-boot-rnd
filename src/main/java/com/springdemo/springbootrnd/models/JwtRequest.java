package com.springdemo.springbootrnd.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest(@JsonProperty("username") String username, @JsonProperty("password") String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}