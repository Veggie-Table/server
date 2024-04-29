package com.vaggietable.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaggietable.server.domain.Review;
import com.vaggietable.server.dto.ReviewResponseDto;
import com.vaggietable.server.mapper.ReviewMapper;
import com.vaggietable.server.mapper.UserMapper;
import com.vaggietable.server.service.CustomOAuth2UserService;
import com.vaggietable.server.controller.MainController;
import com.vaggietable.server.dto.ReviewRequestDto;
import com.vaggietable.server.service.MainService;
import com.vaggietable.server.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReviewTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mvc;
    @LocalServerPort
    private int port;

    @MockBean
    private MainService mainService;

    @MockBean
    private UserService userService; // UserService Mock 추가

    @Test
    void saveReview() throws Exception {
        // Given
        Long rID = 1L;
        String content = "테스트용 입니다";
        double score = 5.0;

        ReviewRequestDto dto = new ReviewRequestDto();
        dto.setRId(rID);
        dto.setContent(content);
        dto.setScore(score);

        // UserService의 getCurrentUsername() 메서드의 반환값 설정
        when(userService.getCurrentUsername()).thenReturn("testUser");

        String url = "http://localhost:" + port + "/review";

        // When
        mvc.perform(post(url)
                .with(oauth2Login()
                        .authorities(new SimpleGrantedAuthority("ROLE_GUEST")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        // Then
        verify(mainService, times(1)).saveReview(refEq(dto)); // saveReview 메서드가 호출되었는지 확인
    }
}


