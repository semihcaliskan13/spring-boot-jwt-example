package com.example.springbootjwtexample.api.controller;

import com.example.springbootjwtexample.dto.LoginPayload;
import com.example.springbootjwtexample.dto.LoginResponse;
import com.example.springbootjwtexample.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public LoginResponse login(@RequestBody LoginPayload loginPayload){
        return authService.login(loginPayload);
    }
}
