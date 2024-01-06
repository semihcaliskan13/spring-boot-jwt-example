package com.example.springbootjwtexample.service.impl;

import com.example.springbootjwtexample.exception.UserNotFoundException;
import com.example.springbootjwtexample.model.User;
import com.example.springbootjwtexample.repository.UserRepository;
import com.example.springbootjwtexample.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(String id) {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByUsername(String username) {
        return repository.getUserByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (repository.existsById(user.getId())){
            repository.save(user);
        }
        throw new UserNotFoundException(String.format("User not found with id: %s",user.getId()));
    }

    @Override
    public void deleteUserById(String id) {
        repository.deleteById(id);
    }
}
