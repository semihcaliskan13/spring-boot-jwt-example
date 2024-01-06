package com.example.springbootjwtexample.api.controller;

import com.example.springbootjwtexample.dto.LoginPayload;
import com.example.springbootjwtexample.dto.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/login")
@RestController
public class AuthController {

    @PostMapping
    public void login(@RequestBody LoginPayload loginPayload){

    }
}
