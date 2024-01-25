package com.vaggietable.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaggietable.server.controller.MainController;
import com.vaggietable.server.dto.ReviewRequestDto;
import com.vaggietable.server.service.MainService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MainController.class)
public class ReviewTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MainService mainService;

    @Test
    void saveReview() throws Exception {

        ReviewRequestDto requestDto = new ReviewRequestDto();
        requestDto.setContent("Test");
        requestDto.setScore(4.5);

        mockMvc.perform(post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());

    }
}
