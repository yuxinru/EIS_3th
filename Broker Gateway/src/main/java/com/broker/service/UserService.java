package com.broker.service;

import com.broker.dao.UserDAO;
import com.broker.entity.User;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public interface UserService {
    User loadUserByUsername(String s);

    int login(User user);

    int registerUser(User user);
}
