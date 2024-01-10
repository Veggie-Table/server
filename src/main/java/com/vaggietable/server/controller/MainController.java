package com.vaggietable.server.controller;

import com.vaggietable.server.config.auth.OAuthAttributes;
import com.vaggietable.server.domain.User;
import com.vaggietable.server.dto.NicknameDto;
import com.vaggietable.server.mapper.UserMapper;
import com.vaggietable.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {
    private final UserMapper userMapper;
    private final UserService userService;

    public MainController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
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






}
