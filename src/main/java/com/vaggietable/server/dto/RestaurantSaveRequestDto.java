package com.vaggietable.server.dto;

import com.vaggietable.server.domain.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Setter
@Getter
@NoArgsConstructor
public class RestaurantSaveRequestDto {
    private String rName;
    private double latitude;
    private double longitude;
  /*  @Nullable
    private Long reviewId;*/
    private String category;
    private String address;
    private String menu1;
    private String menu2;

}
