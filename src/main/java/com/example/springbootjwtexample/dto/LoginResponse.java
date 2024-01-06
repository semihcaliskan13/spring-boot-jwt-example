package com.example.springbootjwtexample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginResponse {
    private String accessToken;
    private String expiration;
}
