package com.vaggietable.server.controller;

import com.vaggietable.server.domain.User;
import com.vaggietable.server.dto.NicknameDto;
import com.vaggietable.server.dto.RestaurantResponseDto;
import com.vaggietable.server.dto.RestaurantSaveRequestDto;
import com.vaggietable.server.dto.ReviewRequestDto;
import com.vaggietable.server.mapper.UserMapper;
import com.vaggietable.server.service.MainService;
import com.vaggietable.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
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

    @PostMapping("/checkNickname")
    public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestBody Map<String, String> request) {
        String nickname = request.get("nickname");
        boolean exists = userService.checkNickname(nickname);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/review")
    public ResponseEntity<String> saveReview(ReviewRequestDto dto){
        User user = userMapper.findByEmail(userService.getCurrentUserEmail());
        ReviewRequestDto requestDto = new ReviewRequestDto();
        requestDto.setEmail(user.getEmail());
        requestDto.setContent(dto.getContent());
        requestDto.setScore(dto.getScore());
        requestDto.setRId(dto.getRId());
        mainService.saveReview(dto);
        return ResponseEntity.ok("리뷰작성완료");
    }



    @PostMapping("/restaurant")
    public ResponseEntity<String> saveRestaurant(@RequestBody RestaurantSaveRequestDto dto){
        mainService.saveRestaurantInfo(dto);
        return ResponseEntity.ok("식당정보 등록 완료");
    } //아직 지도 api 연결하기 전이라 더미데이터로 식당정보 등록하기 위해 만든 api 입니다

    @GetMapping("/category")
    public ResponseEntity<?> findCategory(@RequestParam String category){
        List<RestaurantResponseDto> responseDtoList = mainService.findCategory(category);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/byViews")
    public ResponseEntity<List<RestaurantResponseDto>> getByViewsOrder(){
        List<RestaurantResponseDto> responseDtoList = mainService.getByViewsOrder();
        return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    } //조회순 정렬

}
