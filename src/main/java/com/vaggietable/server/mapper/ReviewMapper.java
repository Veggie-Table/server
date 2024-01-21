package com.vaggietable.server.mapper;

import com.vaggietable.server.dto.ReviewRequestDto;

public interface ReviewMapper {
     void saveReview(ReviewRequestDto dto);
}
