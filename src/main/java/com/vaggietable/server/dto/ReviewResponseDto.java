package com.vaggietable.server.dto;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class ReviewResponseDto {
    private String content;
    private double score;
    private double scoreAvg;
    private LocalDateTime writtenDate;

}
