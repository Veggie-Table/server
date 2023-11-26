package com.vaggietable.server.service;

import com.vaggietable.server.domain.User;
import com.vaggietable.server.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public User findNicknameById(String email){
        return userMapper.findNicknameById(email);
    }
}
