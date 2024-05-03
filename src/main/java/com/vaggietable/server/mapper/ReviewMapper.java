package com.vaggietable.server.mapper;

import com.vaggietable.server.dto.ReviewDeleteDto;
import com.vaggietable.server.dto.ReviewRequestDto;
import com.vaggietable.server.dto.ReviewResponseDto;
import com.vaggietable.server.dto.ReviewUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReviewMapper {
     void saveReview(ReviewRequestDto dto);
     void updateReview(ReviewUpdateDto dto);

     @Select("select * from review where rID = #{rId}")
     List<ReviewResponseDto> findRestaurantReview(Long rId);

     @Select("select * from review where username = #{username}")
     List<ReviewResponseDto> findmyReview(String username);

     void deleteReview(ReviewDeleteDto dto);
}
