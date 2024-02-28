package com.vaggietable.server.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Review {
    @Id
    private Long reviewId;
    private Long rId; //식당아이디
    private String content; //리뷰내용
    private float score; //별점
    private Date writtenDate; //리뷰작성날짜
}
