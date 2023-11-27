package com.vaggietable.server.controller;

import com.vaggietable.server.domain.User;
import com.vaggietable.server.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {
    private final UserMapper userMapper;
    public MainController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/")
    public String home_html() {
        return "home_html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/signup_nick")
    public String signup_nick() {
        return "signup_nick";
    }
/*    @PostMapping ("nickname/{id}")
    public User findNicknameById (@RequestBody String nickname){

    }*/
}
