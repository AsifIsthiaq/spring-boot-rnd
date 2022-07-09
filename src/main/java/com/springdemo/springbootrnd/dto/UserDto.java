package com.springdemo.springbootrnd.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID userId;
    private String fullName;
    private String email;
    private String phone;
    private String photo;
}
