package com.vaggietable.server.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class reviewRequestDto {
    private Long rId;
    private String nickname;
    private String content;
    private float score;

}
