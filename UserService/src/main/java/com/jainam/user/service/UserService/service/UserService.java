package com.jainam.user.service.UserService.service;

import com.jainam.user.service.UserService.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

}
