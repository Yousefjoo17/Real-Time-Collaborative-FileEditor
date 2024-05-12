package com.Demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Demo.model.User;
import com.Demo.repository.UserRepository;
import com.Demo.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    
    @Override
    public String createUser(User user) {
        userRepository.save(user);
        return "sucess";
    }

    @Override
    public User getUser(int userID) {
     return userRepository.findById(userID).get();
    }

    @Override
    public List<User> getAllUsers() {
     return userRepository.findAll();   
    }

}
