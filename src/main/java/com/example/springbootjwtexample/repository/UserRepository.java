package com.example.springbootjwtexample.repository;

import com.example.springbootjwtexample.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
}
