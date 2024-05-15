package com.maboroshi.cave.service;

import com.maboroshi.cave.pojo.User;

public interface UserService {
    User login(User user);

    void add(User user);

    void update(User user);

    User getUser(Integer userId);
}
