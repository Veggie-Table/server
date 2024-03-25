package com.vaggietable.server.mapper;

import com.vaggietable.server.domain.User;
import com.vaggietable.server.dto.NicknameDto;
import com.vaggietable.server.dto.ReviewRequestDto;
import com.vaggietable.server.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.ComponentScan;

@Mapper
@ComponentScan
public interface UserMapper {
    //로그인 & 회원가입
    User findByEmail(String email);
    UserDTO findByUsername(String username);
    String checkNickname(String nickname);
    void save(UserDTO user);
    void saveNickname(NicknameDto dto);

    String findNickname(NicknameDto dto);

    void saveReview(ReviewRequestDto dto);

}