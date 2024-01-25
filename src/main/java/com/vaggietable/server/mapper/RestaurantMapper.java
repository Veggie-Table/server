package com.vaggietable.server.mapper;

import com.vaggietable.server.dto.RestaurantResponseDto;
import com.vaggietable.server.dto.RestaurantSaveRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    void saveRestaurantInfo(RestaurantSaveRequestDto restaurant);
    List<RestaurantResponseDto> findCategory(String category);

    List<RestaurantResponseDto> getByViewsOrder();
}
