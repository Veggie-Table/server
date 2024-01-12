package com.vaggietable.server.controller;

import com.vaggietable.server.domain.User;
import com.vaggietable.server.mapper.UserMapper;
import com.vaggietable.server.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ModelController {

    private final UserMapper userMapper;
    private final UserService userService;

    public ModelController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/home_gps_o")
    public String home_gps_o (Model model){
        User user = userMapper.findByEmail(userService.getCurrentUserEmail());
        String userNickname = user.getNickname();
        model.addAttribute("nickname", userNickname);
        return "home_gps_o";
    }



}
