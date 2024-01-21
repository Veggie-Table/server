package com.vaggietable.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    private Long rId;

    private double latitude;
    private double longitude;
/*    private Long scrapId;
    private Long reviewId*/;
    private String category;
    private String address;
    private String menu1;
    private String menu2;
    private String rName;
}
