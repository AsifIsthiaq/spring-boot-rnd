package com.springdemo.springbootrnd.controllers;

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

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@Valid @NonNull @RequestBody User user) throws Exception {
        System.out.println("ReqBody /register "+user);
        return new ResponseEntity(userService.registerUser(user), HttpStatus.CREATED);
    }
}
