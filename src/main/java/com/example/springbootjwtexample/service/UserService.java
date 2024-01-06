package com.example.springbootjwtexample.service;

import com.example.springbootjwtexample.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(String id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUserById(String id);
}
