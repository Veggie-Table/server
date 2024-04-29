package com.vaggietable.server.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    private Long rId;

    private double latitude;
    private double longitude;
    private long bookmarkId;
    private List<Review> reviewId;
    private String category;
    private String address;
    private String menu1;
    private String menu2;
    private String rName;
    private int views;
}
