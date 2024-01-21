package com.vaggietable.server.service;

import com.vaggietable.server.dto.RestaurantResponseDto;
import com.vaggietable.server.dto.RestaurantSaveRequestDto;
import com.vaggietable.server.dto.ReviewRequestDto;
import com.vaggietable.server.mapper.RestaurantMapper;
import com.vaggietable.server.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {
    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;

    public MainService(UserMapper userMapper, RestaurantMapper restaurantMapper) {
        this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
    }

    public void saveReview(ReviewRequestDto dto){
        userMapper.saveReview(dto);
    }
    public void saveRestaurantInfo(RestaurantSaveRequestDto dto){
        RestaurantSaveRequestDto requestDto = new RestaurantSaveRequestDto();
        requestDto.setLatitude(dto.getLatitude());
        requestDto.setLongitude(dto.getLongitude());
        requestDto.setCategory(dto.getCategory());
        requestDto.setAddress(dto.getAddress());
        requestDto.setMenu1(dto.getMenu1());
        requestDto.setMenu2(dto.getMenu2());
        restaurantMapper.saveRestaurantInfo(dto);
    }

    public List<RestaurantResponseDto> findCategory(String category){
        return restaurantMapper.findCategory(category);
    }

}
