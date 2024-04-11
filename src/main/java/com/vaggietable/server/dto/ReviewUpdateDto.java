package com.vaggietable.server.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Setter
@Getter
public class ReviewUpdateDto {
    @Id
    private Long reviewId;
    private String content;
    private double score;
    private String email;
}
