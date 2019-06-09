package com.trader.service;

import com.trader.entity.User;

public interface UserService {
    User loadUserByUsername(String s);

    int login(User user);

    int registerUser(User user);
}
