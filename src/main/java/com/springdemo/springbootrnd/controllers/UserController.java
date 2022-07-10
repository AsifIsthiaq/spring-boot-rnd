package com.springdemo.springbootrnd.controllers;

import com.springdemo.springbootrnd.converters.UserConverter;
import com.springdemo.springbootrnd.dto.UserDto;
import com.springdemo.springbootrnd.models.Person;
import com.springdemo.springbootrnd.models.User;
import com.springdemo.springbootrnd.services.PersonService;
import com.springdemo.springbootrnd.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/user")
@RestController
@Tag(name = "User", description = "The User API. Contains all the operations that can be performed on a user.")
public class UserController {
    private UserService userService;
    private UserConverter userConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDto> registerUser(@Valid @NonNull @RequestBody User user) throws Exception {
        System.out.println("ReqBody /register " + user);
        return new ResponseEntity(
                userConverter.entityToDto(userService.registerUser(user)),
                HttpStatus.CREATED);
    }

    @Operation(summary = "", description = "Get all users")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity(
                userConverter.entityToDto(userService.getUsers()),
                HttpStatus.OK);
    }

    @Operation(summary = "", description = "Get user")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") UUID id) {
        return new ResponseEntity(
                userConverter.entityToDto(userService.getUserById(id)),
                HttpStatus.OK);
    }
}
