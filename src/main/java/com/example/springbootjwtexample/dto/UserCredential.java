package com.example.springbootjwtexample.dto;

import com.example.springbootjwtexample.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCredential {
    private String id;
    private String username;
    private String password;
    private List<Role> roles;
}
