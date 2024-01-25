package com.vaggietable.server.dto;
import com.vaggietable.server.domain.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class RestaurantResponseDto {
    private long rId;
    private String category;
    private String address;
    private String menu1;
    private String menu2;
    private String rName;
    private List<Review> reviewId;

}
//메인, 카테고리