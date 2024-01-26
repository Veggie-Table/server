package com.vaggietable.server;
import com.vaggietable.server.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.vaggietable.server.config.auth.CustomOAuth2UserService;

import javax.servlet.http.HttpSession;

@Configuration
public class CustomOAuth2UserServiceConfig {

    private final UserMapper userMapper;
    private final HttpSession httpSession;

    public CustomOAuth2UserServiceConfig(UserMapper userMapper, HttpSession httpSession) {
        this.userMapper = userMapper;
        this.httpSession = httpSession;
    }

    @Bean
    public CustomOAuth2UserService customOAuth2UserService() {
        return new CustomOAuth2UserService(userMapper, httpSession);
    }
}