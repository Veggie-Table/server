package com.vaggietable.server.controller;

import com.vaggietable.server.domain.User;
import com.vaggietable.server.dto.NicknameDto;
import com.vaggietable.server.dto.RestaurantResponseDto;
import com.vaggietable.server.dto.RestaurantSaveRequestDto;
import com.vaggietable.server.dto.ReviewRequestDto;
import com.vaggietable.server.jwt.JWTUtil;
import com.vaggietable.server.mapper.UserMapper;
import com.vaggietable.server.service.MainService;
import com.vaggietable.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @PostMapping("/checkNickname")
    public ResponseEntity<Map<String, Boolean>> checkNickname(@RequestBody Map<String, String> request) {
        String nickname = request.get("nickname");
        boolean exists = userService.checkNickname(nickname);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveNickname")
    public String saveNickname(@RequestParam String nickname, RedirectAttributes attributes) {
        User user = userMapper.findByEmail(userService.getCurrentUserEmail());
        NicknameDto dto = new NicknameDto();
        dto.setEmail(user.getEmail());
        dto.setNickname(nickname);
        userService.saveNickname(dto);

        attributes.addAttribute("nickname", nickname); // 닉네임을 쿼리 매개변수로 전달

        return "redirect:/home_gps_o";
    }
    @GetMapping("/home_gps_o")
    public String home(Model model, @RequestParam(name = "nickname") String nickname) {
        // 다른 정보를 가져오는 로직
        // 예: 가장 가까운 맛집 정보 가져오기 등
        if (nickname != null) {
            model.addAttribute("nickname", nickname);
        }
        // 다른 정보도 모델에 추가
        // model.addAttribute("someOtherData", someOtherData);

        return "home_gps_o";
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
    }

    @GetMapping("/home_category_o")
    public String category() {
        return "/home_category_o";
    }

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
