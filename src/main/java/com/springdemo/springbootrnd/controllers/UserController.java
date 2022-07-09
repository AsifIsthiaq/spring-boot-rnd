package com.springdemo.springbootrnd.controllers;

import com.springdemo.springbootrnd.converters.UserConverter;
import com.springdemo.springbootrnd.dto.UserDto;
import com.springdemo.springbootrnd.models.Person;
import com.springdemo.springbootrnd.models.User;
import com.springdemo.springbootrnd.services.PersonService;
import com.springdemo.springbootrnd.services.UserService;
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

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity(
                userConverter.entityToDto(userService.getUsers()),
                HttpStatus.OK);
    }
}
