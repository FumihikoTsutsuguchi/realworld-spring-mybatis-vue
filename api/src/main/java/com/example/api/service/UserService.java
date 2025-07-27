package com.example.api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.mapper.UserMapper;
import com.example.api.model.User;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional
    public User register(String username, String email, String rawPassword) {

        User newUser = new User(null, username, email, rawPassword, LocalDateTime.now());
        int id = userMapper.insert(newUser);

        // ★ withId で id を埋めたコピーを返す
        return newUser.withId(id);
    }
}