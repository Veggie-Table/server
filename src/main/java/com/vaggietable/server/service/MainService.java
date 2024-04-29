package com.vaggietable.server.service;

import com.vaggietable.server.domain.User;
import com.vaggietable.server.dto.*;
import com.vaggietable.server.mapper.RestaurantMapper;
import com.vaggietable.server.mapper.ReviewMapper;
import com.vaggietable.server.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MainService {
    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;
    private final ReviewMapper reviewMapper;
    private final UserService userService;

    public MainService(UserMapper userMapper, RestaurantMapper restaurantMapper, ReviewMapper reviewMapper, UserService userService) {
        this.userMapper = userMapper;
        this.restaurantMapper = restaurantMapper;
        this.reviewMapper = reviewMapper;
        this.userService = userService;
    }



    public void saveRestaurantInfo(RestaurantSaveRequestDto dto){
        RestaurantSaveRequestDto requestDto = new RestaurantSaveRequestDto();
        requestDto.setRName(dto.getRName());
        requestDto.setLatitude(dto.getLatitude());
        requestDto.setLongitude(dto.getLongitude());
        requestDto.setCategory(dto.getCategory());
        requestDto.setAddress(dto.getAddress());
        requestDto.setMenu1(dto.getMenu1());
        requestDto.setMenu2(dto.getMenu2());
        restaurantMapper.saveRestaurantInfo(dto);
    }

    public Long saveReview(ReviewRequestDto dto){
        UserDTO user = userMapper.findByUsername(userService.getCurrentUsername());
        ReviewRequestDto requestDto = new ReviewRequestDto();
        requestDto.setUsername(user.getUsername());
        requestDto.setNickname(user.getNickname());
        requestDto.setContent(dto.getContent());
        requestDto.setScore(dto.getScore());
        requestDto.setRId(dto.getRId());
        requestDto.setWrittenDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        reviewMapper.saveReview(dto);
        return dto.getReviewId();
    }

    public void updateReview(ReviewUpdateDto dto){
        ReviewUpdateDto updateDto = new ReviewUpdateDto();
        updateDto.setReviewId(dto.getReviewId());
        updateDto.setScore(dto.getScore());
        updateDto.setContent(dto.getContent());
        reviewMapper.updateReview(updateDto);
    }

    public List<ReviewResponseDto> findRestaurantReview(Long rId){
        return reviewMapper.findRestaurantReview(rId);
    }


    public List<RestaurantResponseDto> findCategory(String category){
        return restaurantMapper.findCategory(category);
    }

    public List<RestaurantResponseDto> getByViewsOrder(){
        return restaurantMapper.getByViewsOrder();
    }


}
