package com.springdemo.springbootrnd.controllers;

import java.util.Objects;

import com.springdemo.springbootrnd.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.springdemo.springbootrnd.services.JwtUserDetailsService;

import com.springdemo.springbootrnd.util.JwtTokenUtil;
import com.springdemo.springbootrnd.models.JwtRequest;
import com.springdemo.springbootrnd.models.JwtResponse;

@RequestMapping("api/v1/auth")
@RestController
@CrossOrigin
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil,
                          JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());
        final String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        return new ResponseEntity(new JwtResponse(accessToken, refreshToken), HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}