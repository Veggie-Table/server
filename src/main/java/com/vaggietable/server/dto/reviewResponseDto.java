package com.vaggietable.server.dto;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class reviewResponseDto {
    private String content;
    private float score;
    private LocalDateTime writtenDate;

}
