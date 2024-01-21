package com.vaggietable.server.mapper;

import com.vaggietable.server.domain.User;
import com.vaggietable.server.dto.NicknameDto;
import com.vaggietable.server.dto.ReviewRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //로그인 & 회원가입
    User findByEmail(String email);
    String checkNickname(String nickname);
    void save(User user);
    void saveNickname(NicknameDto dto);

    String findNickname(NicknameDto dto);

    void saveReview(ReviewRequestDto dto);

}