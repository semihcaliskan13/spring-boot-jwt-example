package com.example.springbootjwtexample.dto;

import com.example.springbootjwtexample.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class UserCredential {
    private String id;
    private String username;
    private String password;
    private List<Role> roles;
}
