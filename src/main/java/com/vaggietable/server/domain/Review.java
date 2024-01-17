package com.vaggietable.server.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class Review {
    private Long reviewId;
    private Long rId;
    private String content;
    private float score;
}
