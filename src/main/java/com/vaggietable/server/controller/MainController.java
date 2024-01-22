package com.vaggietable.server.controller;

import com.vaggietable.server.domain.User;
import com.vaggietable.server.dto.NicknameDto;
import com.vaggietable.server.dto.RestaurantResponseDto;
import com.vaggietable.server.dto.RestaurantSaveRequestDto;
import com.vaggietable.server.dto.ReviewRequestDto;
import com.vaggietable.server.mapper.UserMapper;
import com.vaggietable.server.service.MainService;
import com.vaggietable.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    private final UserMapper userMapper;
    private final UserService userService;

    private  final MainService mainService;

    public MainController(UserMapper userMapper, UserService userService, MainService mainService) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.mainService = mainService;
    }


    @GetMapping("/")
    public String home_html() {
        return "home_login_x";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/signup_nick")
    public String signup_nick() {
        return "signup_nick";
    }

    @PostMapping("/saveNickname")
    public String saveNickname(@RequestParam String nickname) {
        User user = userMapper.findByEmail(userService.getCurrentUserEmail());
        NicknameDto dto = new NicknameDto();
        dto.setEmail(user.getEmail());
        dto.setNickname(nickname);
        userService.saveNickname(dto);
        return "home_gps_o";
    }

    @GetMapping("/nickname")
    public String checkNickname(@RequestParam String nickname){
       Boolean isExist;
       String userNickname = userService.checkNickname(nickname);
       if(userNickname!=null){
           isExist = true;
       }else {
           isExist = false;
       }
        return Boolean.toString(isExist);
    }

    @PostMapping("/review")
    public ResponseEntity<String> saveReview(ReviewRequestDto dto){
        User user = userMapper.findByEmail(userService.getCurrentUserEmail());
        ReviewRequestDto requestDto = new ReviewRequestDto();
        requestDto.setEmail(user.getEmail());
        requestDto.setContent(dto.getContent());
        requestDto.setScore(dto.getScore());
        mainService.saveReview(dto);
        return ResponseEntity.ok("리뷰작성완료");

    }

    @PostMapping("/restaurant")
    public ResponseEntity<String> saveRestaurant(@RequestBody RestaurantSaveRequestDto dto){
        mainService.saveRestaurantInfo(dto);
        return ResponseEntity.ok("식당정보 등록 완료");
    }

    @GetMapping("/category")
    public ResponseEntity<?> findCategory(@RequestParam String category){
        List<RestaurantResponseDto> responseDtoList = mainService.findCategory(category);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }


}
