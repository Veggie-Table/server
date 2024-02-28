package com.vaggietable.server.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class reviewResponseDto {
    private String content;
    private float score;
    private Date writtenDate;
}
