package com.maboroshi.cave.service.impl;

import com.maboroshi.cave.mapper.UserMapper;
import com.maboroshi.cave.pojo.User;
import com.maboroshi.cave.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User getUser(Integer userId) {
        return userMapper.getUser(userId);
    }
}
