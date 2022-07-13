package com.springdemo.springbootrnd.controllers;

import com.springdemo.springbootrnd.config.CustomAuthenticationManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import com.springdemo.springbootrnd.services.CustomUserDetailsService;

import com.springdemo.springbootrnd.util.JwtTokenUtil;
import com.springdemo.springbootrnd.models.JwtRequest;
import com.springdemo.springbootrnd.models.JwtResponse;

import javax.validation.Valid;

@RequestMapping("api/auth")
@RestController
//@CrossOrigin
@Tag(name = "Auth", description = "The Auth API. Contains all the operations for authentication & new token generation.")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final CustomAuthenticationManager customAuthenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          CustomAuthenticationManager customAuthenticationManager,
                          JwtTokenUtil jwtTokenUtil,
                          CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.customAuthenticationManager = customAuthenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> login(@Valid @NonNull @RequestBody JwtRequest request) throws Exception {
        authenticate(request.getUsername(), request.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());
        final String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        return new ResponseEntity(new JwtResponse(accessToken, refreshToken), HttpStatus.OK);
    }

    @Operation(summary = "", description = "Get new access & refresh tokens")
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(value = "/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestAttribute String username) throws Exception {
        System.out.println("refresh controller username: " + username);
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
        final String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        return new ResponseEntity(new JwtResponse(accessToken, refreshToken), HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
//           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            customAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}