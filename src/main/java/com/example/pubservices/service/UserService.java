package com.example.pubservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pubservices.model.User;
import com.example.pubservices.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return this.userRepository.save(user);
    }
}
