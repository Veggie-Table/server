package com.vaggietable.server.mapper;

import com.vaggietable.server.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userMapper {
    //로그인 & 회원가입
    User findByEmail(String email);
    void save(User user);
}