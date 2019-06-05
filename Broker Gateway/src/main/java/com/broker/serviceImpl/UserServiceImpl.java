package com.broker.serviceImpl;

import com.broker.dao.UserDAO;
import com.broker.entity.User;
import com.broker.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDAO userDAO;

    @Override
    public User loadUserByUsername(String s) {
        User user = userDAO.getUserByUsername(s).get(0);
        return user;
    }
    @Override
    public int login(User user) {
        User user1 = userDAO.getUserByUsername(user.getUsername()).get(0);
        if(user1 == null)
            return 0;
        if(user.getPassword().equals(user1.getPassword()))
            return 1;
        return -1;
    }
    @Override
    public int registerUser(User user) {
        if(userDAO.getUserByUsername(user.getUsername()).size() != 0){
            return -1;
        }
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encode = encoder.encode(password);
        User user1 = new User();
        user1.setUsername(user.getUsername());
        //user.setPassword(encode);
        user1.setRole(user.getPassword());
        user1.setRole("ROLE_USER");
        return userDAO.insert(user1);
    }
}
