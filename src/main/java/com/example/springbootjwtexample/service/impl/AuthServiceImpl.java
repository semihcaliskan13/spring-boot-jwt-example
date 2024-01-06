package com.example.springbootjwtexample.service.impl;

import com.example.springbootjwtexample.auth.CustomUserDetails;
import com.example.springbootjwtexample.dto.LoginPayload;
import com.example.springbootjwtexample.dto.LoginResponse;
import com.example.springbootjwtexample.dto.UserCredential;
import com.example.springbootjwtexample.model.User;
import com.example.springbootjwtexample.service.AuthService;
import com.example.springbootjwtexample.service.UserService;
import com.example.springbootjwtexample.util.auth.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthServiceImpl(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public LoginResponse login(LoginPayload loginPayload) {
        String username = loginPayload.getUsername();
        var authentication = new UsernamePasswordAuthenticationToken(username,loginPayload.getPassword());
        var auth = authenticationManager.authenticate(authentication);
        return generateToken(username);
    }

    private LoginResponse generateToken(String username) {

        User user = userService.getUserByUsername(username);
        UserCredential userCredentials = new UserCredential();
        BeanUtils.copyProperties(user,userCredentials);
        CustomUserDetails userDetails = new CustomUserDetails(userCredentials);
        String token = jwtUtil.generateToken(userDetails);
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }

}
