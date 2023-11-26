package com.vaggietable.server.mapper;

import com.vaggietable.server.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //로그인 & 회원가입
    User findByEmail(String email);
    User findNicknameById(String email);
    void save(User user);
}