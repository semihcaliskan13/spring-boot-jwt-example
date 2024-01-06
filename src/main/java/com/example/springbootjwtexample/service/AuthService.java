package com.example.springbootjwtexample.service;

import com.example.springbootjwtexample.dto.LoginPayload;
import com.example.springbootjwtexample.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginPayload loginPayload);

}
