package com.example.yasir.Auth.controller;

import com.example.yasir.Auth.bo.LoginRequest;
import com.example.yasir.Auth.bo.LoginResponse;
import com.example.yasir.Auth.bo.RegisterRequest;
import com.example.yasir.Auth.bo.RegisterResponse;
import com.example.yasir.Auth.entity.User;
import com.example.yasir.Auth.service.AuthenticationService;
import com.example.yasir.Auth.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerUserRequest) {
//        User registeredUser = authenticationService.signup(registerUserDto);

        User registeredUser = authenticationService.signup(registerUserRequest);
        String jwtToken = jwtService.generateToken(registeredUser);

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setToken(jwtToken);
        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}