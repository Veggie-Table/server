package com.vaggietable.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaggietable.server.dto.ReviewRequestDto;
import com.vaggietable.server.service.MainService;
import com.vaggietable.server.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
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
                        .authorities(new SimpleGrantedAuthority("ROLE_GUEST"))) //소셜로그인을 구현했을 때 인증
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        // Then
        verify(mainService, times(1)).saveReview(refEq(dto)); // saveReview 메서드가 호출되었는지 확인 refEq안써주면 다른 객체로 인지함
    }
}


