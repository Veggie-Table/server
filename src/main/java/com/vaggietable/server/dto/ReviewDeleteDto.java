package com.vaggietable.server.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class ReviewDeleteDto {
    @Id
    private Long reviewId;
    private String email;
    
}
