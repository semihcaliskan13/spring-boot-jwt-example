package com.example.springbootjwtexample.dto;

import lombok.Data;

@Data
public class LoginPayload {
    private String username;
    private String password;
}
