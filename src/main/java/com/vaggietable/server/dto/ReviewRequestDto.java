package com.vaggietable.server.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Setter
@Getter
public class ReviewRequestDto {

    @Id
    private Long reviewId;
    private Long rId;
    private String username;
    private String content;
    private double score;
    private LocalDateTime writtenDate;


}
