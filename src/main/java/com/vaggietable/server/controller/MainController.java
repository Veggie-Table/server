package com.vaggietable.server.controller;

import com.vaggietable.server.dto.*;
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
import java.util.stream.Collectors;

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
    public ResponseEntity<Map<String, Object>> checkNickname(@RequestBody Map<String, String> request) {
        String nickname = request.get("nickname");
        boolean exists = userService.checkNickname(nickname);
        Map<String, Object> response = new HashMap<>();
        if(!exists){
            response.put("exists", exists);
            response.put("success",true);
            response.put("message","닉네임 사용 가능");
            return ResponseEntity.ok(response);
        }
        else{
            response.put("success",false);
            response.put("message","이미 사용중인 닉네임");
            return ResponseEntity.status(403).body(response);
        }
    }

    @PostMapping("/saveNickname")
    public String saveNickname(@RequestParam String nickname, RedirectAttributes attributes) {
        UserDTO user = userMapper.findByUsername(userService.getCurrentUsername());
        NicknameDto dto = new NicknameDto();
        dto.setEmail(user.getEmail());
        dto.setNickname(nickname);
        userService.saveNickname(dto);

        attributes.addAttribute("nickname", nickname); // 닉네임을 쿼리 매개변수로 전달

        return "redirect:home_gps_o";
    }

    @GetMapping("/home_gps_o")
    public String home(Model model, @RequestParam(name = "nickname") String nickname) {
        // 다른 정보를 가져오는 로직
        // 예: 가장 가까운 맛집 정보 가져오기 등

        // 조회순으로 정렬된 맛집 정보 가져오기
        ResponseEntity<List<RestaurantResponseDto>> responseEntity = getByViewsOrder();
        List<RestaurantResponseDto> responseDtoList = responseEntity.getBody();

        // 최대 7위까지의 맛집 정보만 유지
        List<RestaurantResponseDto> top7Restaurants = responseDtoList.stream().limit(7).collect(Collectors.toList());

        // 모델에 맛집 정보 추가
        model.addAttribute("top7Restaurants", top7Restaurants);

        if (nickname != null) {
            model.addAttribute("nickname", nickname);
        }
        // 다른 정보도 모델에 추가
        // model.addAttribute("someOtherData", someOtherData);

        return "home_gps_o";
    }

    @PostMapping("/review")
    public  ResponseEntity<Map<String, Object>> saveReview(@RequestBody ReviewRequestDto dto){
        Map<String, Object> response = new HashMap<>();
        if (dto.getContent()==null|| Double.valueOf(dto.getScore()) == null || dto.getRId() == null) {
            // 필요한 필드가 비어있는 경우
            response.put("success", false);
            response.put("message", "데이터가 비어있음");
            return ResponseEntity.badRequest().body(response);
        }else {
            Long reviewId = mainService.saveReview(dto);
            response.put("success", true);
            response.put("message", "리뷰 작성 완료." );
            response.put("reviewId", reviewId);
            return ResponseEntity.ok(response);
        }

    }
    @PutMapping("/review")
    public  ResponseEntity<Map<String, Object>> updateReview(@RequestBody ReviewUpdateDto dto){
        Long reviewId = dto.getReviewId();
        UserDTO user = userMapper.findByUsername(userService.getCurrentUsername());
        dto.setUsername(user.getUsername());
        Map<String, Object> response = new HashMap<>();
        if(reviewId!=null||user!=null){
            mainService.updateReview(dto);
            response.put("success",true);
            response.put("message","리뷰수정완료");
            return ResponseEntity.ok().body(response);
        }
        else{
            response.put("success",false);
            response.put("message","해당하는 리뷰가 존재하지 않음");
            return ResponseEntity.status(404).body(response);
        }

    }

    @GetMapping("/rReview/{rId}")
    public ResponseEntity<Map<String,Object>> findRestaurantReview (@RequestParam Long rId){
        List<ReviewResponseDto> responseDtoList = mainService.findRestaurantReview(rId);
        Map<String, Object> response = new HashMap<>();
        if(responseDtoList.isEmpty()){
            response.put("success",false);
            response.put("message","해당 식당의 리뷰가 존재하지 않음");
            return ResponseEntity.ok(response);
        }else {
            response.put("success",true);
            response.put("reviews",responseDtoList);
            return ResponseEntity.ok(response);
        }

    }
    @GetMapping("/myReview")
    public ResponseEntity<Map<String,Object>> findmyReview (String username){
        List<ReviewResponseDto> responseDtoList = mainService.findmyReview(username);
        Map<String, Object> response = new HashMap<>();
        if(responseDtoList.isEmpty()){
            response.put("success",false);
            response.put("message","해당 식당의 리뷰가 존재하지 않음");
            return ResponseEntity.ok(response);
        }else {
            response.put("success",true);
            response.put("reviews",responseDtoList);
            return ResponseEntity.ok(response);
        }

    }
    @DeleteMapping ("/review{reviewId}")
    public ResponseEntity<Map<String,Object>> deleteReview (@RequestBody ReviewDeleteDto dto){
        Map<String, Object> response = new HashMap<>();
        if(dto.getReviewId()==null){
            response.put("success",false);
            response.put("message","해당하는 리뷰가 없습니다.");
            return ResponseEntity.status(404).body(response);
        }else {
            mainService.deleteReview(dto);
            response.put("success",true);
            response.put("message","리뷰가 삭제되었습니다.");
            return ResponseEntity.ok(response);
        }
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
        Map<String, Object> response = new HashMap<>();
        try {
            if (responseDtoList.isEmpty()) {
                response.put("success", false);
                response.put("message", "해당 식당 없음");
                return ResponseEntity.status(404).body(response);
            } else {
                return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
            }
        } catch (IllegalArgumentException e){
            response.put("success",false);
            response.put("message", "카테고리가 존재하지 않음");
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/byViews")
    public ResponseEntity<List<RestaurantResponseDto>> getByViewsOrder(){
        List<RestaurantResponseDto> responseDtoList = mainService.getByViewsOrder();
        return new ResponseEntity<>(responseDtoList,HttpStatus.OK);
    } //조회순 정렬


}
