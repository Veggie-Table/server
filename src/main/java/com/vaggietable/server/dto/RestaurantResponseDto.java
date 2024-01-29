package com.vaggietable.server.dto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaggietable.server.domain.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RestaurantResponseDto {
    @Id
    @JsonProperty("rId")
    private long rId;

    @JsonProperty("category")
    private String category;

    @JsonProperty("address")
    private String address;

    @JsonProperty("menu1")
    private String menu1;

    @JsonProperty("menu2")
    private String menu2;

    @JsonProperty("rName")
    private String rName;

    @JsonProperty("reviewId")
    private List<Review> reviewId;

    // Constructors, getters, setters, etc.
}
//메인, 카테고리