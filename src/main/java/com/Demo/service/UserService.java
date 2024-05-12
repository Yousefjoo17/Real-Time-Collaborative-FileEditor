package com.Demo.service;

import com.Demo.model.User;

public interface UserService {
    public String createUser(User user);
    public User getUser(int userID);
}
