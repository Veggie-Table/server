package com.vaggietable.server.mapper;

import com.vaggietable.server.dto.ReviewRequestDto;
import com.vaggietable.server.dto.ReviewUpdateDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
     void saveReview(ReviewRequestDto dto);
     void updateReview(ReviewUpdateDto dto);
}
