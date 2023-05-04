package com.springdemo.springbootrnd.controllers;

import com.springdemo.springbootrnd.dao.caching.RedisDataAccessService;
import com.springdemo.springbootrnd.models.LogoutResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.springdemo.springbootrnd.services.CustomUserDetailsService;

import com.springdemo.springbootrnd.util.JwtTokenUtil;
import com.springdemo.springbootrnd.models.LoginRequest;
import com.springdemo.springbootrnd.models.JwtResponse;

import javax.validation.Valid;
import java.util.Map;

@RequestMapping("api/v1/auth")
@RestController
//@CrossOrigin
@Tag(name = "Auth", description = "The Auth API. Contains all the operations for authentication & new token generation.")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private RedisDataAccessService redisDataAccessService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil,
                          CustomUserDetailsService customUserDetailsService,
                          RedisDataAccessService redisDataAccessService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.redisDataAccessService = redisDataAccessService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> login(@Valid @NonNull @RequestBody LoginRequest request) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(request.getUsername());
        final String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        return new ResponseEntity(new JwtResponse(accessToken, refreshToken), HttpStatus.OK);
    }

    @Operation(summary = "", description = "Revoke token")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(value = "/logout")
    public ResponseEntity<LogoutResponse> logout(@RequestHeader Map<String, String> headers) {
        String bearerToken = headers.get("authorization").substring(7);
        this.redisDataAccessService.save(bearerToken, true);
        System.out.println(bearerToken);
        return new ResponseEntity(new LogoutResponse("Token revoked"), HttpStatus.OK);
    }

    @Operation(summary = "", description = "Get new access & refresh tokens")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestAttribute String username) throws Exception {
        System.out.println("refresh controller username: " + username);
        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(username);
        final String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        return new ResponseEntity(new JwtResponse(accessToken, refreshToken), HttpStatus.OK);
    }
}
