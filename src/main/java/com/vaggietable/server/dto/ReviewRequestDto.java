package com.vaggietable.server.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewRequestDto {
    private Long rId;
    private String email;
    private String content;
    private float score;

}
