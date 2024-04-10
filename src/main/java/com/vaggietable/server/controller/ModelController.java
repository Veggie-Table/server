package com.vaggietable.server.controller;

import com.vaggietable.server.domain.User;
import com.vaggietable.server.service.CustomOAuth2User;
import com.vaggietable.server.dto.RestaurantResponseDto;
import com.vaggietable.server.mapper.UserMapper;
import com.vaggietable.server.service.MainService;
import com.vaggietable.server.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ModelController {

    private final UserMapper userMapper;
    private final UserService userService;

    private final MainService mainService;

    public ModelController(UserMapper userMapper, UserService userService, MainService mainService) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.mainService = mainService;
    }


    @GetMapping("/category")
    public String showRestaurantsByCategory(@RequestParam String category, Model model) {
        List<RestaurantResponseDto> restaurants = mainService.findCategory(category);
        model.addAttribute("restaurants",restaurants);
        return "home_category_o"; //
    }

}
